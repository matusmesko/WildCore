package cz.wildcraft.wildcore.gdpr;

import java.util.Date;

public class GdprModel {
    private String username;
    private boolean accepted;
    private String accept_date;

    public GdprModel(String username, boolean accepted, String accept_date) {
        this.username = username;
        this.accepted = accepted;
        this.accept_date = accept_date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getAccept_date() {
        return accept_date;
    }

    public void setAccept_date(String accept_date) {
        this.accept_date = accept_date;
    }
}
