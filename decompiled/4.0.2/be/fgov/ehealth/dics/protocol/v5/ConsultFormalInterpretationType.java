package be.fgov.ehealth.dics.protocol.v5;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultFormalInterpretationType",
   propOrder = {"rule", "additionalFields", "reimbursementConditions", "reimbursementTerms"}
)
public class ConsultFormalInterpretationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Rule",
      required = true
   )
   protected String rule;
   @XmlElement(
      name = "AdditionalFields"
   )
   protected List<AdditionalFieldsType> additionalFields;
   @XmlElement(
      name = "ReimbursementCondition"
   )
   protected List<ConsultReimbursementConditionType> reimbursementConditions;
   @XmlElement(
      name = "ReimbursementTerm"
   )
   protected List<ConsultReimbursementTermType> reimbursementTerms;
   @XmlAttribute(
      name = "Key",
      required = true
   )
   protected String key;
   @XmlAttribute(
      name = "StartDate",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlAttribute(
      name = "EndDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;

   public ConsultFormalInterpretationType() {
   }

   public String getRule() {
      return this.rule;
   }

   public void setRule(String value) {
      this.rule = value;
   }

   public List<AdditionalFieldsType> getAdditionalFields() {
      if (this.additionalFields == null) {
         this.additionalFields = new ArrayList();
      }

      return this.additionalFields;
   }

   public List<ConsultReimbursementConditionType> getReimbursementConditions() {
      if (this.reimbursementConditions == null) {
         this.reimbursementConditions = new ArrayList();
      }

      return this.reimbursementConditions;
   }

   public List<ConsultReimbursementTermType> getReimbursementTerms() {
      if (this.reimbursementTerms == null) {
         this.reimbursementTerms = new ArrayList();
      }

      return this.reimbursementTerms;
   }

   public String getKey() {
      return this.key;
   }

   public void setKey(String value) {
      this.key = value;
   }

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }

   public DateTime getEndDate() {
      return this.endDate;
   }

   public void setEndDate(DateTime value) {
      this.endDate = value;
   }
}
