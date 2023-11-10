package org.com.vn.util;

import org.apache.logging.log4j.util.Strings;

public class StringUtils {

    private static final String THREE_ZERO_CHARACTERS = "000";
    private static String readGroup(String group){
        String[] readDigit = {" Không", " Một", " Hai", " Ba", " Bốn", " Năm", " Sáu", " Bẩy", " Tám", " Chín" };
        String temp;
        if (group.equals(THREE_ZERO_CHARACTERS)) return "";
        //read number hundreds
        temp = readDigit[Integer.parseInt(group.substring(0,1))] + " Trăm";
        String readLastDigit = readDigit[Integer.parseInt(group.substring(2,3))];
        //read number tens
        if (group.charAt(1) == '0')
            return group.endsWith("0") ? temp : (temp + " Lẻ" + readLastDigit);

        else
            temp += readDigit[Integer.parseInt(group.substring(1,2))] + " Mươi";
        //read number

        if (group.endsWith("5")) {
            temp += " Lăm";
        } else if (!group.endsWith("0")) {
            temp += readLastDigit;
        }
        return temp;
    }

    public static String readNumber2Vietnamese(String numb){
        if(org.apache.commons.lang3.StringUtils.isBlank(numb)) return "";
        String temp = Strings.EMPTY;

        //length <= 18
        StringBuilder numBuilder = new StringBuilder(numb);
        while (numBuilder.length() < 18)
        {
            numBuilder.insert(0, "0");
        }
        numb = numBuilder.toString();

        String g1 = numb.substring(0, 3);
        String g2 = numb.substring(3, 6);
        String g3 = numb.substring(6, 9);
        String g4 = numb.substring(9, 12);
        String g5 = numb.substring(12,15);
        String g6 = numb.substring(15,18);

        //read group1 ---------------------
        if (!g1.equals(THREE_ZERO_CHARACTERS)){
            temp = readGroup(g1);
            temp += " Triệu";
        }
        //read group2-----------------------
        if (!g2.equals(THREE_ZERO_CHARACTERS)){
            temp += readGroup(g2);
            temp += " Nghìn";
        }
        //read group3 ---------------------
        if (!g3.equals(THREE_ZERO_CHARACTERS)){
            temp += readGroup(g3);
            temp += " Tỷ";
        } else if(!"".equals(temp)){
            temp += " Tỷ";
        }

        //read group2-----------------------
        if (!g4.equals(THREE_ZERO_CHARACTERS)){
            temp += readGroup(g4);
            temp += " Triệu";
        }
        //---------------------------------
        if (!g5.equals(THREE_ZERO_CHARACTERS)){
            temp += readGroup(g5);
            temp += " Nghìn";
        }
        //-----------------------------------

        temp = temp + readGroup(g6);
        //---------------------------------
        // Refine
        temp = temp.replace("Một Mươi", "Mười");
        temp = temp.trim();
        temp = temp.replace("Mười Không", "Mười");
        temp = temp.trim();
        temp = temp.replace("Mươi Không", "Mươi");
        temp = temp.trim();
        temp = temp.replace("Mươi Một", "Mươi Mốt");
        temp = temp.trim();
        temp = temp.replace("Lẻ Một", "Linh Một");
        temp = temp.trim();
        if (temp.startsWith("Không Trăm ")) {
            temp = temp.substring(10);
        }
        temp = temp.trim();
        if (temp.startsWith("Linh ")) {
            temp = temp.substring(5);
        }
        temp = temp.trim();
        if (temp.startsWith("Lẻ ")) {
            temp = temp.substring(3);
        }
        temp = temp.trim();
        //Change Case
        return temp.substring(0, 1).toUpperCase() + temp.substring(1).toLowerCase() + " đồng chẵn./.";
    }

}
