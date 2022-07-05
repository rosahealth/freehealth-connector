package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDSEVERITY;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "severityType",
   propOrder = {"cd"}
)
public class SeverityType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDSEVERITY cd;

   public SeverityType() {
   }

   public CDSEVERITY getCd() {
      return this.cd;
   }

   public void setCd(CDSEVERITY value) {
      this.cd = value;
   }
}
