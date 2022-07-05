package be.fgov.ehealth.rn.baselegaldata.v1;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.rn.commons.business.v1.LocalizedDescriptionType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractOptionalSubregisterType",
   propOrder = {"subregisterCode", "subregisterDescriptions", "inceptionDate"}
)
@XmlSeeAlso({SubregisterBaseType.class, SubregisterWithStatusAndSourceType.class})
public abstract class AbstractOptionalSubregisterType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SubregisterCode"
   )
   @XmlSchemaType(
      name = "unsignedShort"
   )
   protected Integer subregisterCode;
   @XmlElement(
      name = "SubregisterDescription"
   )
   protected List<LocalizedDescriptionType> subregisterDescriptions;
   @XmlElement(
      name = "InceptionDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime inceptionDate;

   public AbstractOptionalSubregisterType() {
   }

   public Integer getSubregisterCode() {
      return this.subregisterCode;
   }

   public void setSubregisterCode(Integer value) {
      this.subregisterCode = value;
   }

   public List<LocalizedDescriptionType> getSubregisterDescriptions() {
      if (this.subregisterDescriptions == null) {
         this.subregisterDescriptions = new ArrayList();
      }

      return this.subregisterDescriptions;
   }

   public DateTime getInceptionDate() {
      return this.inceptionDate;
   }

   public void setInceptionDate(DateTime value) {
      this.inceptionDate = value;
   }
}
