package ch.mbolli.awt.auth3000;

import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Persist {
    private XStream xstream = null;
    private Persist(){
        xstream = new XStream();
        xstream.ignoreUnknownElements();
    }
    /**
     * Convert any given Object to a XML String
     * @param object
     * @return String
     */
    public String toXMLString(Object object) {
        return xstream.toXML(object);
    }
    /**
     * Convert given XML to an Object
     * @param xml
     * @return object
     */
    public Object toObject(String xml) {
        return (Object) xstream.fromXML(xml);
    }
    /**
     * return this class instance
     * @return
     */
    public static Persist getInstance(){
        return new Persist();
    }
    /**
     * convert to Object from given File
     * @param xmlFile
     * @return object
     * @throws IOException
     */
    public Object toObject(File xmlFile) throws IOException {
        return xstream.fromXML(new FileReader(xmlFile));
    }
    /**
     * create XML file from the given object with custom file name
     * @param objTobeXMLTranslated
     * @param fileName
     * @throws IOException
     */
    public void toXMLFile(Object objTobeXMLTranslated, String fileName ) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        xstream.toXML(objTobeXMLTranslated, writer);
        writer.close();
    }
    public void toXMLFile(Object objTobeXMLTranslated, String fileName, List omitFieldsRegXList) throws IOException {
        xstreamInitializeSettings(objTobeXMLTranslated, omitFieldsRegXList);
        toXMLFile(objTobeXMLTranslated, fileName);
    }
    /**
     * @
     * @param objTobeXMLTranslated
     */
    public void xstreamInitializeSettings(Object objTobeXMLTranslated, List omitFieldsRegXList) {
        if(omitFieldsRegXList != null && omitFieldsRegXList.size() > 0){
            Iterator itr = omitFieldsRegXList.iterator();
            while(itr.hasNext()){
                String omitEx = itr.next().toString();
                xstream.omitField(objTobeXMLTranslated.getClass(), omitEx);
            }
        }
    }
    /**
     * create XML file from the given object, file name is generated automatically (class name)
     * @param objTobeXMLTranslated
     * @throws IOException
     */
    public void toXMLFile(Object objTobeXMLTranslated) throws IOException {
        toXMLFile(objTobeXMLTranslated,objTobeXMLTranslated.getClass().getName()+".xml");
    }

    public Users getUsers() {
        try {
            Users users = (Users) toObject(new File("/var/lib/tomcat7/webapps/Auth3000/resources/users.xml"));
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return new Users();
        }
    }

    public void setUsers(Users users) {
        try {
            toXMLFile(users, "/var/lib/tomcat7/webapps/Auth3000/resources/users.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(UserBean user) {
        Users users = getUsers(); // comment out to create first user
        //Users users = new Users(); // comment out after
        users.addUser(user);
        setUsers(users);
        return true;
    }

    public Accounts getAccounts() {
        try {
            Accounts accounts = (Accounts) toObject(new File("/var/lib/tomcat7/webapps/Auth3000/resources/accounts.xml"));
            return accounts;
        } catch(Exception e) {
            e.printStackTrace();
            return new Accounts();
        }
    }

    public List<AccountBean> getUserAccounts() {
        Accounts accounts = this.getAccounts();
        return accounts.getUserAccounts();
    }

    public void setAccounts(Accounts accounts) {
        try {
            toXMLFile(accounts, "/var/lib/tomcat7/webapps/Auth3000/resources/accounts.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addAccount(AccountBean account) {
        Accounts accounts = getAccounts();
        //Accounts accounts = new Accounts();
        accounts.addAccount(account);
        setAccounts(accounts);
        return true;
    }

}
