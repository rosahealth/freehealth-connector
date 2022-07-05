package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-AUTONOMYvalues"
)
@XmlEnum
public enum CDAUTONOMYvalues {
   @XmlEnumValue("homebound")
   HOMEBOUND("homebound");

   private final String value;

   private CDAUTONOMYvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDAUTONOMYvalues fromValue(String v) {
      CDAUTONOMYvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDAUTONOMYvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
