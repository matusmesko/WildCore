package cz.wildcraft.wildcore.vote;

public class VoteModel {
    private String username;
    private String service;
    private String vote_date;

    public VoteModel(String username, String service, String vote_date) {
        this.username = username;
        this.service = service;
        this.vote_date = vote_date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVote_date() {
        return vote_date;
    }

    public void setVote_date(String vote_date) {
        this.vote_date = vote_date;
    }
}
