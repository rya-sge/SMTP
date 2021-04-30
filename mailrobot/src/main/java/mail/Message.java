package mail;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Message {
    private String from;
    private String body;
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
        System.arraycopy(cc, 0, this.cc, 0, cc.size());
    }

    /**
     *
     * @param to
     */
    public void setTo(List<String> to) {
        System.arraycopy(to, 0, this.to, 0, to.size());
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
