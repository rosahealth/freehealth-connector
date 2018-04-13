package org.taktik.freehealth.middleware.drugs.logic.impl;

import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taktik.freehealth.middleware.dao.CodeDao;
import org.taktik.freehealth.middleware.dto.Code;
import org.taktik.freehealth.middleware.drugs.*;
import org.taktik.freehealth.middleware.drugs.Atc;
import org.taktik.freehealth.middleware.drugs.civics.*;
import org.taktik.freehealth.middleware.drugs.dao.DrugsDAO;
import org.taktik.freehealth.middleware.drugs.dto.DocExtendedInfos;
import org.taktik.freehealth.middleware.drugs.dto.DocId;
import org.taktik.freehealth.middleware.drugs.dto.DocPreview;
import org.taktik.freehealth.middleware.drugs.dto.FullTextSearchResult;
import org.taktik.freehealth.middleware.drugs.dto.IamFullInfos;
import org.taktik.freehealth.middleware.drugs.dto.MpExtendedInfos;
import org.taktik.freehealth.middleware.drugs.dto.MpFullInfos;
import org.taktik.freehealth.middleware.drugs.dto.MpId;
import org.taktik.freehealth.middleware.drugs.dto.MpPreview;
import org.taktik.freehealth.middleware.drugs.dto.MppId;
import org.taktik.freehealth.middleware.drugs.dto.MppInfos;
import org.taktik.freehealth.middleware.drugs.dto.MppPreview;
import org.taktik.freehealth.middleware.drugs.logic.DrugsLogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@HibernateDatasource
@Repository
public class DrugsLogicImpl implements DrugsLogic {
    protected final Log log = LogFactory.getLog(getClass());

    private DrugsDAO drugsDAO;
    private MapperFacade mapper;
    private CodeDao codeDao;

    private final static List<String> availableLanguages = Arrays.asList("fr", "nl");
    private final static String defaultLanguage = "fr";

    private final Transformer<Therapy, TherapyInfos> THERAPY_TO_THERAPYINFOS = new Transformer<Therapy, TherapyInfos>() {
        public TherapyInfos transform(Therapy therapy) {
            return mapper.map(therapy, TherapyInfos.class);
        }
    };

    private final Transformer<Verse, VerseInfos> VERSE_TO_VERSEINFOS = new Transformer<Verse, VerseInfos>() {
        @Override
        public VerseInfos transform(Verse verse) {
            VerseInfos verseInfos = mapper.map(verse, VerseInfos.class);
            verseInfos.setVerses(new TreeSet<>(CollectionUtils.collect(drugsDAO.getChildrenVerses(verse), VERSE_TO_VERSEINFOS)));

            return verseInfos;
        }
    };

    private final Transformer<Paragraph, ParagraphInfos> PARAGRAPH_TO_PARAGRAPHINFOS = new Transformer<Paragraph, ParagraphInfos>() {
        public ParagraphInfos transform(Paragraph paragraph) {
            ParagraphInfos paragraphInfos = mapper.map(paragraph, ParagraphInfos.class);

            paragraphInfos.setHeaderVerse(VERSE_TO_VERSEINFOS.transform(drugsDAO.getHeaderVerse(paragraph)));

            return paragraphInfos;
        }
    };

    private final Transformer<AddedDocument, AddedDocumentPreview> ADDDOC_TO_ADDDOCPREVIEW = new Transformer<AddedDocument, AddedDocumentPreview>() {
        public AddedDocumentPreview transform(AddedDocument addedDocument) {
            AddedDocumentPreview documentPreview = mapper.map(addedDocument, AddedDocumentPreview.class);

            documentPreview.setDescrFr(drugsDAO.getShortText(addedDocument.getAppendixType().getNameId(), "FR"));
            documentPreview.setDescrNl(drugsDAO.getShortText(addedDocument.getAppendixType().getNameId(), "NL"));

            return documentPreview;
        }
    };

    private final Transformer<Paragraph, ParagraphPreview> PARAGRAPH_TO_PARAGRAPHPREVIEW = new Transformer<Paragraph, ParagraphPreview>() {
        public ParagraphPreview transform(Paragraph paragraph) {
            return mapper.map(paragraph, ParagraphPreview.class);
        }
    };

    private final Transformer<Code, MppPreview> INN_TO_MPPPREVIEW_FR = inn -> {
		MppPreview mppPreview = new MppPreview();

		mppPreview.setName(inn.getLabel().get("fr"));
		mppPreview.setInncluster(inn.getCode());
		return mppPreview;
	};

    private final Transformer<Code, MppPreview> INN_TO_MPPPREVIEW_NL = inn -> {
		MppPreview mppPreview = new MppPreview();

		mppPreview.setName(inn.getLabel().get("nl"));
		mppPreview.setInncluster(inn.getCode());
		return mppPreview;
	};
    private final Transformer<Mpp, MppPreview> MPP_TO_MPPPREVIEW = new Transformer<Mpp, MppPreview>() {
        public MppPreview transform(Mpp mpp) {
            return mapper.map(mpp, MppPreview.class);
        }
    };

    private final Transformer<Iam, IamFullInfos> IAM_TO_IAMFULL = new Transformer<Iam, IamFullInfos>() {
        public IamFullInfos transform(Iam iam) {
            return mapper.map(iam, IamFullInfos.class);
        }
    };

    private final Transformer<Mpp, MppInfos> MPP_TO_MPPINFOS = new Transformer<Mpp, MppInfos>() {
        public MppInfos transform(Mpp mpp) {
            return mapper.map(mpp, MppInfos.class);
        }
    };

    private final Transformer<Mp, MpExtendedInfos> MP_TO_MPEXTENDEDINFOS = new Transformer<Mp, MpExtendedInfos>() {
        public MpExtendedInfos transform(Mp mp) {
            return mapper.map(mp, MpExtendedInfos.class);
        }
    };

    private final Transformer<Mp, MpFullInfos> MP_TO_MPFULLINFOS = new Transformer<Mp, MpFullInfos>() {
        public MpFullInfos transform(Mp mp) {
            return mapper.map(mp, MpFullInfos.class);
        }
    };

    private final Transformer<Mp, MpPreview> MP_TO_MPPREVIEW = new Transformer<Mp, MpPreview>() {
        public MpPreview transform(Mp mp) {
            return mapper.map(mp, MpPreview.class);
        }
    };

    private final Transformer<Equivalence, MpPreview> EQUIV_TO_MPPREVIEW = new Transformer<Equivalence, MpPreview>() {
        public MpPreview transform(Equivalence eq) {
            return mapper.map(eq.getMpByTargetequivalence(), MpPreview.class);
        }
    };

    private final Transformer<Doc, DocExtendedInfos> DOC_TO_DOCEXTENDEDINFOS = new Transformer<Doc, DocExtendedInfos>() {
        public DocExtendedInfos transform(Doc doc) {
            return mapper.map(doc, DocExtendedInfos.class);
        }
    };

    private final Transformer<Doc, DocPreview> DOC_TO_DOCPREVIEW = new Transformer<Doc, DocPreview>() {
        public DocPreview transform(Doc doc) {
            return mapper.map(doc, DocPreview.class);
        }
    };

    public List<MppPreview> getMedecinePackages(String searchString, String lang, List<String> types, int first, int count) {
        Validate.noNullElements(new Object[]{searchString, lang});
        log.debug("Asked language : " + lang);
        lang = getAvailableLanguage(lang);
        log.debug("Final language : " + lang);
        List<Mpp> packages = drugsDAO.getMedecinePackages(searchString, lang, types, first, count);
        return (List<MppPreview>) CollectionUtils.collect(packages, MPP_TO_MPPPREVIEW);
    }


    public List<MppPreview> getMedecinePackagesFromIngredients(String searchString, String lang, List<String> types, int first, int count) {
        Validate.noNullElements(new Object[]{searchString, lang});
        log.debug("Asked language : " + lang);
        lang = getAvailableLanguage(lang);
        log.debug("Final language : " + lang);
        List<Mpp> packages = drugsDAO.getMedecinePackagesFromIngredients(searchString, lang, types, first, count);
        return (List<MppPreview>) CollectionUtils.collect(packages, MPP_TO_MPPPREVIEW);
    }

    @Override
    public List<MppPreview> getMedecinePackagesFromInn(String inn, String lang) {
        lang = getAvailableLanguage(lang);

        return (List<MppPreview>) CollectionUtils.collect(drugsDAO.getMppsWithInn(inn, lang), MPP_TO_MPPPREVIEW);
    }


    public MpExtendedInfos getExtendedMpInfos(MppId medecinePackageID) {
        return getExtendedMpInfos(drugsDAO.getInfos(medecinePackageID).getMp().getId());
    }

    public MpFullInfos getFullMpInfos(MppId medecinePackageID) {
        Mpp mpp = drugsDAO.getInfos(medecinePackageID);
        if (mpp == null) {
            return null;
        }
        return getFullMpInfos(mpp.getMp().getId());
    }

    public List<MpPreview> getCheapAlternativesBasedOnAtc(MppId medecinePackageID) {
        try {
            Atc atc = drugsDAO.getAtc(medecinePackageID);

            return (List<MpPreview>) CollectionUtils.collect(drugsDAO.getMpsWithAtc(atc), MP_TO_MPPREVIEW);
        } catch (Exception ignored) {
        }
        return new ArrayList<>();
    }

    public List<MppPreview> getCheapAlternativesBasedOnInn(String innCluster, String lang) {
        try {
            lang = getAvailableLanguage(lang);
            return (List<MppPreview>) CollectionUtils.collect(drugsDAO.getCheapMppsWithInn(innCluster, lang), MPP_TO_MPPPREVIEW);
        } catch (Exception ignored) {
        }
        return new ArrayList<>();
    }

    @Override
    public List<ParagraphPreview> findParagraphs(String searchString, String lang) {
        lang = getAvailableLanguage(lang);
        return (List<ParagraphPreview>) CollectionUtils.collect(drugsDAO.findParagraphs(searchString, lang), PARAGRAPH_TO_PARAGRAPHPREVIEW);
    }

    @Override
    public List<ParagraphPreview> findParagraphsWithCnk(Long cnk, String lang) {
        lang = getAvailableLanguage(lang);
        return (List<ParagraphPreview>) CollectionUtils.collect(drugsDAO.findParagraphsWithCnk(cnk, lang), PARAGRAPH_TO_PARAGRAPHPREVIEW);
    }

    public MpExtendedInfos getExtendedMpInfos(MpId medecineID) {
        Mp mp = drugsDAO.getExtendedInfos(medecineID);
        return MP_TO_MPEXTENDEDINFOS.transform(mp);
    }

    public MppInfos getInfos(MppId medecinePackageID) {
        Mpp mpp = drugsDAO.getInfos(medecinePackageID);
        if (mpp==null) {
            return null;
        }
        MppInfos mppInfos = MPP_TO_MPPINFOS.transform(mpp);

        Atc atc = drugsDAO.getAtc(medecinePackageID);

        if (atc != null) {
            mppInfos.setAtcCode(atc.getCode());
        }

        return mppInfos;
    }

    public String getAvailableLanguage(String proposedLanguage) {
        if (proposedLanguage==null) { return defaultLanguage; }
        if (!availableLanguages.contains(proposedLanguage) && proposedLanguage.length()>=2) {
            proposedLanguage = proposedLanguage.substring(0,2).toLowerCase();
        }
        if (!availableLanguages.contains(proposedLanguage)) {
            return defaultLanguage;
        }
        return proposedLanguage;
    }

    public DocExtendedInfos getExtendedDocInfos(DocId docID) {
        Doc doc = drugsDAO.getExtendedInfos(docID);
        DocExtendedInfos result = DOC_TO_DOCEXTENDEDINFOS.transform(doc);
        result.setNext(getNextDoc(docID));
        result.setPrevious(getPreviousDoc(docID));
        if ((doc.getChildren().size() > 0) && (doc.getChildren().get(0).getMpgrp())) {
            result.setMpGroups((List<DocExtendedInfos>) CollectionUtils.collect(doc.getChildren(), DOC_TO_DOCEXTENDEDINFOS));
        }
        return result;
    }

    public DocPreview getNextDoc(DocId docID) {
        Doc doc = drugsDAO.getDoc(docID);
        Doc result;
        DocPreview resultPreview;
        log.info("children size = " + doc.getChildren().size());
        if ((doc.getChildren().size() > 0) && (!doc.getChildren().get(0).getMpgrp())) {
            result = doc.getChildren().get(0);
        } else {
            result = doc;
            boolean finished = false;
            do {
                Doc previous = result;
                result = result.getParent();
                if (result != null) {
                    int index = result.getChildren().indexOf(previous);
                    if ((index + 1) < result.getChildren().size()) {
                        finished = true;
                        result = result.getChildren().get(index + 1);
                    }
                } else {
                    int index = previous.getDocindex() - 1;
                    List<Doc> roots = drugsDAO.getRootDocs(docID.getLang());
                    if ((index + 1) < roots.size()) {
                        result = roots.get(index + 1);
                    }
                }
            } while ((result != null) && !finished);
        }
        if (result == null) {
            resultPreview = null;
        } else {
            resultPreview = DOC_TO_DOCPREVIEW.transform(result);
        }
        return resultPreview;
    }


    public DocPreview getPreviousDoc(DocId docID) {
        Doc doc = drugsDAO.getDoc(docID);
        Doc result;
        DocPreview resultPreview;
        if (doc.getDocindex() > 1) {
            if (doc.getParent() == null) {
                result = drugsDAO.getRootDocs(docID.getLang()).get(doc.getDocindex() - 2);
            } else {
                result = doc.getParent().getChildren().get(doc.getDocindex() - 2);
            }
        } else {
            result = doc.getParent();
        }
        if (result == null) {
            resultPreview = null;
        } else {
            resultPreview = DOC_TO_DOCPREVIEW.transform(result);
        }
        return resultPreview;
    }

    public DrugsDAO getDrugsDAO() {
        return drugsDAO;
    }

    public MpFullInfos getFullMpInfos(MpId mpId) {
        Mp fullMp = drugsDAO.getFullMpInfos(mpId);
        MpFullInfos result = MP_TO_MPFULLINFOS.transform(fullMp);
        if ((fullMp.getDoc() != null) && (fullMp.getDoc().getParent() != null)) {
            result.setRelatedDoc(DOC_TO_DOCPREVIEW.transform(fullMp.getDoc().getParent()));
        }
        Set<Equivalence> mps = fullMp.getEquivalencesForSourceequivalence();
        CollectionUtils.collect(mps, EQUIV_TO_MPPREVIEW, result.getEquivalences());

        return result;
    }

    public List<IamFullInfos> getInteractions(MppId newMppId, List<String> otherMpps) {
        String language = newMppId.getLang();
        Atc atc = drugsDAO.getAtc(newMppId);

        Map<String, List<MppId>> otherMeds = new HashMap<>();
        for (String o : otherMpps) {
            MppId medecinePackageID = new MppId(o, language);
            Atc oAtc = drugsDAO.getAtc(medecinePackageID);
            if (oAtc!=null) {
            List<MppId> medsForAtc = otherMeds.get(oAtc.getCode());
            if (medsForAtc == null) {
                otherMeds.put(oAtc.getCode(), medsForAtc = new ArrayList<>());
            }

            medsForAtc.add(medecinePackageID);}
        }


        List<IamFullInfos> result = new ArrayList<>();

        if (atc != null) {
            List<Iam> iams = drugsDAO.getIams(atc.getCode(), language);
            for (Iam iam : iams) {
                List<MppId> mppIds = otherMeds.get(iam.getAtc2());
                if (mppIds != null) {
                    for (MppId id : mppIds) {
                        IamFullInfos transform = IAM_TO_IAMFULL.transform(iam);
                        transform.setMppInfos(MPP_TO_MPPINFOS.transform(drugsDAO.getMpp(id)));
                        result.add(transform);
                    }
                }
            }
        }

        return result;
    }

    public List<FullTextSearchResult> fullTextSearch(String search, String lang, List<String> classes, List<String> types, int from, int count) throws IOException {
        lang = getAvailableLanguage(lang);
        return drugsDAO.fullTextSearch(search, lang, classes, types, from, count);
    }


    public List<DocPreview> getRootDocs(String lang) {
        lang = getAvailableLanguage(lang);
        return (List<DocPreview>) CollectionUtils.collect(drugsDAO.getRootDocs(lang), DOC_TO_DOCPREVIEW);
    }

    public DocPreview getDocPreview(String id, String lang) {
        lang = getAvailableLanguage(lang);
        return DOC_TO_DOCPREVIEW.transform(drugsDAO.getDoc(new DocId(id, lang)));
    }


    public List<DocPreview> getChildrenDocs(DocId docID) {
        Doc aDoc = drugsDAO.getDoc(docID);
        List<Doc> childrenDocs = aDoc.getChildren();
        if (childrenDocs.size() > 0) {
            if (childrenDocs.get(0).getMpgrp()) {
                childrenDocs = new ArrayList<>();
            }
        }
        return (List<DocPreview>) CollectionUtils.collect(childrenDocs, DOC_TO_DOCPREVIEW);
    }

    public List<MpPreview> getChildrenMps(DocId docID) {
        Doc aDoc = drugsDAO.getDoc(docID);
        SortedSet<Mp> childrenMps = aDoc.getMps();
        List<Mp> resultMps = new ArrayList<>();
        resultMps.addAll(childrenMps);
        List<Doc> childrenDocs = aDoc.getChildren();
        if (childrenDocs.size() > 0) {
            if (childrenDocs.get(0).getMpgrp()) {
                for (Doc child : childrenDocs) {
                    resultMps.addAll(child.getMps());
                }
            }
        }
        return (List<MpPreview>) CollectionUtils.collect(resultMps, MP_TO_MPPREVIEW);
    }

    public List<TherapyInfos> getTherapiesInfos(MppId mppId) {
        Ampp ampp = drugsDAO.getAmpp(mppId);
        return (List<TherapyInfos>) CollectionUtils.collect(ampp.getAmp().getAtm().getTherapies(), THERAPY_TO_THERAPYINFOS);
    }

    public ParagraphInfos getParagraphInfos(Long therapyId) {
        Therapy therapy = drugsDAO.getTherapy(therapyId);
        Paragraph paragraph = drugsDAO.getParagraph(therapy);

        return PARAGRAPH_TO_PARAGRAPHINFOS.transform(paragraph);
    }

    @Override
    public ParagraphInfos getParagraphInfos(String chapterName, String paragraphName) {
        Paragraph paragraph = drugsDAO.getParagraph(chapterName, paragraphName);

        return paragraph == null ? null : PARAGRAPH_TO_PARAGRAPHINFOS.transform(paragraph);
    }

    @Override
    public List<AddedDocumentPreview> getAddedDocuments(String chapterName, String paragraphName) {
        List<AddedDocument> docs = drugsDAO.getAddedDocuments(chapterName, paragraphName);
        return (List<AddedDocumentPreview>) CollectionUtils.collect(docs, ADDDOC_TO_ADDDOCPREVIEW);
    }

    @Override
    public List<MppPreview> getInnClusters(String region, String searchString, String lang, List<String> types, int first, int count) {
        Validate.noNullElements(new Object[]{searchString, lang});
        log.debug("Asked language : " + lang);
        lang = getAvailableLanguage(lang);
        log.debug("Final language : " + lang);
        List<Code> inns = codeDao.findCodesByLabel(lang, "CD-INNCLUSTER", searchString);
        return (List<MppPreview>) CollectionUtils.collect(inns.subList(first, Math.min(first+count,inns.size())), lang != null && lang.equals("nl") ? INN_TO_MPPPREVIEW_NL : INN_TO_MPPPREVIEW_FR);
    }


    public MpPreview getMpFromMpp(String mppId, String lang) {
        lang = getAvailableLanguage(lang);
        Mpp mpp = drugsDAO.getMpp(new MppId(mppId, lang));
        return MP_TO_MPPREVIEW.transform(mpp.getMp());
    }


    public void installNewDrugsDatabase(String path) {
        drugsDAO.installNewDrugsDatabase(path);
    }


    public boolean isDataBasePresent() {
        return drugsDAO.isDataBasePresent();
    }


    public void initDrugsDatabase() {
        drugsDAO.initDrugsDatabase();
    }

    public void stopDrugsDatabase() {
        drugsDAO.stopDrugsDatabase();
    }


    public DocPreview getDocOfMp(MpId mpId) {
        Mp mp = drugsDAO.getMp(mpId);
        Doc mpGrp = mp.getDoc();
        if (mpGrp == null) {
            return null;
        }
        if (mpGrp.getParent() == null) {
            return null;
        }
        return DOC_TO_DOCPREVIEW.transform(mpGrp.getParent());
    }


    public DocPreview getParentDoc(DocId docID) {
        Doc doc = drugsDAO.getDoc(docID);
        if (doc.getParent() == null) {
            return null;
        }
        return DOC_TO_DOCPREVIEW.transform(doc.getParent());
    }

    @Autowired
    public void setDrugsDAO(DrugsDAO drugsDAO) {
        this.drugsDAO = drugsDAO;
    }

    @Autowired
    public void setMapper(MapperFacade mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setCodeDao(CodeDao codeDao) {
        this.codeDao = codeDao;
    }
}