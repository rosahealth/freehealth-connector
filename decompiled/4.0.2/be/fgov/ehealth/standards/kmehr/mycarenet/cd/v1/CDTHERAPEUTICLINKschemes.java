package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-THERAPEUTICLINKschemes"
)
@XmlEnum
public enum CDTHERAPEUTICLINKschemes {
   @XmlEnumValue("CD-THERAPEUTICLINKTYPE")
   CD_THERAPEUTICLINKTYPE("CD-THERAPEUTICLINKTYPE"),
   LOCAL("LOCAL");

   private final String value;

   private CDTHERAPEUTICLINKschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDTHERAPEUTICLINKschemes fromValue(String v) {
      CDTHERAPEUTICLINKschemes[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDTHERAPEUTICLINKschemes c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
