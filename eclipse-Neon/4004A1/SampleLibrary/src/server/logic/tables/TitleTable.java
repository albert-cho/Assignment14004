package server.logic.tables;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import server.logic.model.Title;
import utilities.Trace;

public class TitleTable {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<Title> titleList=new ArrayList<Title>();
    private static class TitleListHolder {
        private static final TitleTable INSTANCE = new TitleTable();
    }
    private TitleTable(){
    	//set up the default list with some instances
    	String[] ISBNList=new String[]{"9781442668584","9781442616899","9781442667181","9781611687910","9781317594277"};
    	String[] titlenameList=new String[]{"By the grace of God","Dante's lyric poetry ","Courtesy lost","Writing for justice","The act in context"};
    	for(int i=0;i<ISBNList.length;i++){
    		Title detitle=new Title(ISBNList[i],titlenameList[i]);
    		titleList.add(detitle);
		}
    	logger.info(String.format("Operation:Initialize TitleTable;TitleTable: %s", titleList));
    };
    public static final TitleTable getInstance() {
        return TitleListHolder.INSTANCE;
    }
	public Object createtitle(String nISBN, String nTitle) {		
		boolean result=true;
		int flag=0;
		for(int i=0;i<titleList.size();i++){
			String ISBN=(titleList.get(i)).getISBN();
			if(ISBN.equalsIgnoreCase(nISBN)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			Title newtitle=new Title(nISBN,nTitle);
			result=titleList.add(newtitle);
			logger.info(String.format("Operation:Create New Title;Title Info:[%s,%s];State:Success", nISBN,nTitle));
		}else{
			result=false;
			logger.info(String.format("Operation:Create New Title;Title Info:[%s,%s];State:Fail;Reason:The ISBN already existed.", nISBN,nTitle));
		}
		return result;	
	}
	public boolean lookup(String tISBN) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<titleList.size();i++){
			String ISBN=(titleList.get(i)).getISBN();
			if(ISBN.equalsIgnoreCase(tISBN)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			result=false;
		}
		return result;
	}
	public Object delete(String tISBN) {
		String result="";
		int index=0;
		int flag=0;
		for(int i=0;i<titleList.size();i++){
			if(titleList.get(i).getISBN().equalsIgnoreCase(tISBN)){
				flag=flag+1;
				index=i;
			}else{
				flag=flag+0;
			}
		}
		if(flag!=0){
			boolean loan=LoanTable.getInstance().checkLoan(tISBN);
			if(loan){
				String string2=titleList.get(index).getBooktitle();
				ItemTable.getInstance().deleteAll(tISBN);
				titleList.remove(index);
				result="success";
				logger.info(String.format("Operation:Delete Title;Title Info:[%s,%s];State:Success", tISBN,string2));
			}else{
				result="Active Loan Exists";
				logger.info(String.format("Operation:Delete Title;ISBN Info:[%s];State:Fail;Reason:Active Loan Exists.", tISBN));
			}
		}else{
			result="The Title Does Not Exist";
			logger.info(String.format("Operation:Delete Title;ISBN Info:[%s];State:Fail;Reason:The Title Does Not Exist.", tISBN));
		}
		return result;
	}
	public List<Title> getTitleTable() {
		return titleList;
	}
	
	public String getTitleList(){
		String tValues = "";
		for (int i = 0; i < titleList.size(); i++){
			tValues = tValues + titleList.get(i);
			if(i < titleList.size()-1){
				tValues = tValues + ",";
			}
		}
		return tValues;
	}
}
