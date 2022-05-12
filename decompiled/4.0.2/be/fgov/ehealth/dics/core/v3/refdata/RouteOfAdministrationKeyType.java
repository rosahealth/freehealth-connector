package be.fgov.ehealth.dics.core.v3.refdata;

import be.fgov.ehealth.dics.protocol.v3.RouteOfAdministrationWithStandardsType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RouteOfAdministrationKeyType"
)
@XmlSeeAlso({RouteOfAdministrationWithStandardsType.class})
public class RouteOfAdministrationKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   protected String code;

   public RouteOfAdministrationKeyType() {
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }
}
