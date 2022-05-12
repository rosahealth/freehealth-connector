package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RevocationValuesType",
   propOrder = {"crlValues", "ocspValues", "otherValues"}
)
public class RevocationValuesType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CRLValues"
   )
   protected CRLValuesType crlValues;
   @XmlElement(
      name = "OCSPValues"
   )
   protected OCSPValuesType ocspValues;
   @XmlElement(
      name = "OtherValues"
   )
   protected OtherCertStatusValuesType otherValues;
   @XmlAttribute(
      name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;

   public RevocationValuesType() {
   }

   public CRLValuesType getCRLValues() {
      return this.crlValues;
   }

   public void setCRLValues(CRLValuesType value) {
      this.crlValues = value;
   }

   public OCSPValuesType getOCSPValues() {
      return this.ocspValues;
   }

   public void setOCSPValues(OCSPValuesType value) {
      this.ocspValues = value;
   }

   public OtherCertStatusValuesType getOtherValues() {
      return this.otherValues;
   }

   public void setOtherValues(OtherCertStatusValuesType value) {
      this.otherValues = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
