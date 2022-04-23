public class FindNomAuteur {

    public FindNomAuteur(Parser parser) {
        if (parser.getAuthorsTab()[0].equals("")) {
            if (parser.getEmail() != null) {
                String[] emails = parser.getEmail().split(";");
                String[] noms = new String[emails.length];
                String[] prenoms = new String[emails.length];
                String[] authorTab = new String[emails.length];
                for (int i = 0; i < emails.length; i++) {
                    String nomPrenom = emails[i].split("@")[0];
                    prenoms[i] = nomPrenom.split("\\.")[0];
                    try {
                        noms[i] = nomPrenom.split("\\.")[1];
                    } catch (Exception ignored) {
                    }
                    if (noms[i] != null) {
                        authorTab[i] = prenoms[i] + " " + noms[i];
                    } else {
                        authorTab[i] = prenoms[i];
                    }
                }
                parser.setAuthorsTab(authorTab);
            }
        }
    }
}