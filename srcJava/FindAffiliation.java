import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindAffiliation {
    public FindAffiliation(Parser parser) {
//
        String res = "";
        HashMap<String, String> affiliationAuthors = new HashMap<>();

        if (parser.getEmail() != null) {
            String[] emails = parser.getEmail().split(";");
            String[] affiliation;
            for (int i = 0; i < emails.length; i++) {
                String donneesMail = emails[i].split("@")[1];
                affiliation = donneesMail.split("\\.");
                try {
                    res = affiliation[affiliation.length - 2];
                } catch (Exception ignored) {
                    res = "L'affiliation n'a pas pu être trouvée";
                }
                affiliationAuthors.put(emails[i], res);

            }
        } else {
            String affiliation;
            String name1 = "";
            String name2 = "";
            for (String auth : parser.getAuthorsTab()) {
                affiliation = "";
                if (!auth.contains("Impossible")) {
                    try {
                        parser.resetScanner();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    try {
                        name1 = auth.split(" ")[0];
                        name2 = auth.split(" ")[1];
                    } catch (IndexOutOfBoundsException ignored) {
                    }

                    String str;
                    boolean containsName = false;
                    int cpt = 0;

                    while (parser.getScanner().hasNextLine()) {
                        str = parser.getScanner().nextLine();
                        Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(str);

                        if (containsName) {
                            if (cpt == 9 || m.find()) {
                                break;
                            }
                            if (cpt < 5) {
                                if (str.length() > 2 && str.length() < 70 && !containsOtherAuthor(parser, str)) {
                                    affiliation += str + "\n";
                                }
                                cpt++;
                            }
                        }
                        if (!name1.equals("") && !name2.equals("")) {
                            try {
                                String name1Modify = name1;
                                if (name1.length() >= 6) {
                                    name1Modify = name1.substring(1, name1.length() - 1).toUpperCase();
                                }
                                String name2Modify = name2;
                                if (name2.length() >= 6) {
                                    name2Modify = name2.substring(1, name2.length() - 1).toUpperCase();
                                }

                                if (containsWord(str.toUpperCase(), name1Modify) || containsWord(str.toUpperCase(), name2Modify)) {
                                    containsName = true;
                                }
                            } catch (IndexOutOfBoundsException ignored) {
                            }
                        } else if (!name1.equals("")) {
                            String name1Modify = name1;
                            if (name1.length() >= 6) {
                                name1Modify = name1.substring(1, name1.length() - 1).toUpperCase();
                            }
                            try {
                                if (containsWord(str.toUpperCase(), name1Modify)) {
                                    containsName = true;
                                }
                            } catch (IndexOutOfBoundsException ignored) {
                            }
                        } else if (!name2.equals("")) {
                            String name2Modify = name2;
                            if (name2.length() >= 6) {
                                name2Modify = name2.substring(1, name2.length() - 1).toUpperCase();
                            }
                            try {
                                if (containsWord(str.toUpperCase(), name2Modify)) {
                                    containsName = true;
                                }
                            } catch (IndexOutOfBoundsException ignored) {
                            }
                        }
                        if (containsWord(str, "Abstract") || containsWord(str, "ABSTRACT") || containsWord(str, "Introduction") || containsWord(str, "INTRODUCTION")) {
                            break;
                        }
                    }
                }
                String key = name1 + " " + name2;
                affiliationAuthors.put(key, affiliation);

            }

        }
        parser.setAffilations(affiliationAuthors);
    }

    private boolean containsOtherAuthor(Parser parser, String str) {
        boolean contain = false;
        for (String auth : parser.getAuthorsTab()) {
            if (str.length() > 3) {
                if (containsWord(str.toUpperCase(), auth.substring(1, auth.length() - 1).toUpperCase())) {
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