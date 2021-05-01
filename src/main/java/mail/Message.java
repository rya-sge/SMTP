package mail;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private String from;
    private String body;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    private String subject;

    private List<String> to = new ArrayList();
    private List<String> cc = new ArrayList();
    private List<String> bcc = new ArrayList();

    /**
     *
     * @return
     */
    public List<String> getTo() {
        return to;
    }

    /**
     *
     * @return
     */
    public List<String> getCc() {
        return cc;
    }

    /**
     *
     * @return
     */
    public List<String> getBcc() {
        return bcc;
    }

    /**
     *
     * @param cc
     */
    public void setCc(List<String> cc) {
        this.cc.addAll(cc);
    }

    /**
     *
     * @param to
     */
    public void setTo(List<String> to) {
        this.to.addAll(to);
    }


    /**
     *
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     *
     * @return
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @return
     */
    public String getBody() {
        return body;
    }


    /**
     *
     * @return
     */
    public String getFrom(){
        return from;
    }

    /**
     *
     * @param from
     */
    public void setFrom(String from){
        this.from = from;
    }

}
