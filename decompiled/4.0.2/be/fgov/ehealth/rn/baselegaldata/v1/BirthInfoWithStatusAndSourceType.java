package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BirthInfoWithStatusAndSourceType"
)
public class BirthInfoWithStatusAndSourceType extends AbstractOptionalBirthType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Status"
   )
   protected String status;
   @XmlAttribute(
      name = "Source"
   )
   protected String source;

   public BirthInfoWithStatusAndSourceType() {
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String value) {
      this.status = value;
   }

   public String getSource() {
      return this.source;
   }

   public void setSource(String value) {
      this.source = value;
   }
}
