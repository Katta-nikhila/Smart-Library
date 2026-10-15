
package model;

public class ResearchPaper {
    private int paperId;
    private String title;
    private String author;
    private String abstractText;
    private int publicationYear;

    public ResearchPaper(int paperId, String title, String author,
                         String abstractText, int publicationYear) {
        this.paperId = paperId;
        this.title = title;
        this.author = author;
        this.abstractText = abstractText;
        this.publicationYear = publicationYear;
    }

    public int getPaperId() {
        return paperId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    @Override
    public String toString() {
        return "Paper ID: " + paperId
                + "\nTitle: " + title
                + "\nAuthor: " + author
                + "\nAbstract: " + abstractText
                + "\nPublication Year: " + publicationYear;
    }
}
