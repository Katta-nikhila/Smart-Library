
package model;

public class Author {
    private int authorId;
    private String name;
    private String affiliation;

    public Author(int authorId, String name, String affiliation) {
        this.authorId = authorId;
        this.name = name;
        this.affiliation = affiliation;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getName() {
        return name;
    }

    public String getAffiliation() {
        return affiliation;
    }

    @Override
    public String toString() {
        return "Author ID: " + authorId
                + "\nName: " + name
                + "\nAffiliation: " + affiliation;
    }
}
