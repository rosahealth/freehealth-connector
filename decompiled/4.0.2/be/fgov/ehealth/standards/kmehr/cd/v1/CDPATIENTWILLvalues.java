package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-PATIENTWILLvalues"
)
@XmlEnum
public enum CDPATIENTWILLvalues {
   @XmlEnumValue("ntbr")
   NTBR("ntbr"),
   @XmlEnumValue("bloodtransfusionrefusal")
   BLOODTRANSFUSIONREFUSAL("bloodtransfusionrefusal"),
   @XmlEnumValue("intubationrefusal")
   INTUBATIONREFUSAL("intubationrefusal"),
   @XmlEnumValue("euthanasiarequest")
   EUTHANASIAREQUEST("euthanasiarequest"),
   @XmlEnumValue("vaccinationrefusal")
   VACCINATIONREFUSAL("vaccinationrefusal"),
   @XmlEnumValue("organdonationconsent")
   ORGANDONATIONCONSENT("organdonationconsent"),
   @XmlEnumValue("datareuseforclinicalresearchconsent")
   DATAREUSEFORCLINICALRESEARCHCONSENT("datareuseforclinicalresearchconsent"),
   @XmlEnumValue("datareuseforclinicaltrialsconsent")
   DATAREUSEFORCLINICALTRIALSCONSENT("datareuseforclinicaltrialsconsent"),
   @XmlEnumValue("clinicaltrialparticipationconsent")
   CLINICALTRIALPARTICIPATIONCONSENT("clinicaltrialparticipationconsent"),
   @XmlEnumValue("omissionofmedicaldata")
   OMISSIONOFMEDICALDATA("omissionofmedicaldata");

   private final String value;

   private CDPATIENTWILLvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDPATIENTWILLvalues fromValue(String v) {
      CDPATIENTWILLvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDPATIENTWILLvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
