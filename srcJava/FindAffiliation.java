import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindAffiliation {
    public FindAffiliation(Parser parser) {
        String affiliation = "";
        String name1 = "";
        String name2 = "";
        HashMap<String, String> hashfilliation = new HashMap<>();
        for (String auth: parser.getAuthorsTab()) {
            affiliation = "";
            //System.out.println("RESEEEEEEEEEEEEEEEEET");
            if(!auth.contains("Impossible")) {
                try {
                    parser.resetScanner();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    name1 = auth.split(" ")[0];
                    name2 = auth.split(" ")[1];
                } catch (IndexOutOfBoundsException ignored){}
//                System.out.println("NAME 1 : " + name1);
//                System.out.println("NAME 2 : " + name2);


                String str = "";
                boolean containsName = false;
                int cpt = 0;

                while (parser.getScanner().hasNextLine()) {
                    str = parser.getScanner().nextLine();
                    Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(str);

                    if(containsName) {
                        if(cpt == 9 || m.find()) {
                            break;
                        }
                        if(cpt < 5) {
                            if(str.length() > 2 && str.length() < 70 && !containsOtherAuthor(parser, str)) {
                                affiliation += str + "\n";
//                                System.out.println("AFFILIATION : " + str);
                            }
                            cpt++;
                        }
                    }
                    if(!name1.equals("") && !name2.equals("")) {
                        try {
                            String name1Modify = name1;
                            if(name1.length() >= 6) {
                                name1Modify = name1.substring(1, name1.length()-1).toUpperCase();
                            }
                            String name2Modify = name2;
                            if(name2.length() >= 6) {
                                name2Modify = name2.substring(1, name2.length()-1).toUpperCase();
                            }

                            //System.out.println(name1Modify + " " + name2Modify);

                            if(containsWord(str.toUpperCase(), name1Modify) || containsWord(str.toUpperCase(), name2Modify)) {
                                containsName = true;
                            }
                        } catch (IndexOutOfBoundsException ignored){}
                    } else if(!name1.equals("")){
                        String name1Modify = name1;
                        if(name1.length() >= 6) {
                            name1Modify = name1.substring(1, name1.length()-1).toUpperCase();
                        }
                        try {
                            if(containsWord(str.toUpperCase(), name1Modify)) {
                                containsName = true;
                            }
                        } catch (IndexOutOfBoundsException ignored){}
                    } else if(!name2.equals("")){
                        String name2Modify = name2;
                        if(name2.length() >= 6) {
                            name2Modify = name2.substring(1, name2.length()-1).toUpperCase();
                        }
                        try {
                            if(containsWord(str.toUpperCase(), name2Modify)) {
                                containsName = true;
                            }
                        } catch (IndexOutOfBoundsException ignored){}
                    }


                    if (containsWord(str, "Abstract") || containsWord(str, "ABSTRACT") || containsWord(str, "Introduction") || containsWord(str, "INTRODUCTION")) {
                        //System.out.println("TRRRRRRRRRRRRRUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUEEEEEEEEEEEEE");
                        break;
                    }
                }

            }
            String key = name1 + " " + name2;
            hashfilliation.put(key, affiliation);

        }
        parser.setAffilations(hashfilliation);
//        System.out.println("AFFILIATIONS ------------------------------------------------------------------------------------\n");
//        for (String keys: parser.getAffilations().keySet()) {
//            System.out.println("KEY : " + keys);
//            System.out.println("Affiliation : " + parser.getAffilations().get(keys));
//            System.out.println("NEXT \n");
//        }
//        System.out.println("---------------------------------------------------------------------------------------------------");
    }

    private boolean containsOtherAuthor(Parser parser, String str) {
        boolean contain = false;
        for (String auth: parser.getAuthorsTab()) {
            if(str.length() > 3) {
                if(containsWord(str.toUpperCase(), auth.substring(1, auth.length()-1).toUpperCase())) {
                    contain = true;
                    break;
                }
            } else {
                contain = true;
            }

        }
        return contain;
    }



    private boolean containsWord(String str, String word) {
        int i = str.indexOf(word);
        return i >= 0;
    }
}