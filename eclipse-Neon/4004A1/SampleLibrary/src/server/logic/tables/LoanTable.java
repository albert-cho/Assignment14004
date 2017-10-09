package server.logic.tables;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import server.logic.model.Loan;
import utilities.Config;
import utilities.Trace;

public class LoanTable {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<Loan> loanList=new ArrayList<Loan>();
    private static class LoanListHolder {
        private static final LoanTable INSTANCE = new LoanTable();
    }
    private LoanTable(){
    	//set up the default list with some instances
    	Loan loan=new Loan(0,"9781442668584","1",new Date(),"0");
    	loanList.add(loan);
    	logger.info(String.format("Operation:Initialize LoanTable;LoanTable: %s", loanList));
    };
    public static final LoanTable getInstance() {
        return LoanListHolder.INSTANCE;
    }
    public Object createloan(int uID, String nISBN, String cNUM, Date date) {
		String result="";
		boolean user=UserTable.getInstance().lookup(uID);
		boolean isbn=TitleTable.getInstance().lookup(nISBN);
		boolean copynumber=ItemTable.getInstance().lookup(nISBN,cNUM);
		boolean oloan=LoanTable.getInstance().lookup(nISBN,cNUM);
		boolean limit=LoanTable.getInstance().checkLimit(uID);
		boolean fee=FeeTable.getInstance().lookup(uID);
		if(user==false){
			result="User Invalid";
			logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Invalid User.", uID,nISBN,cNUM,dateformat(date)));
		}else if(isbn==false){
			result="ISBN Invalid";
			logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Invalid ISBN.", uID,nISBN,cNUM,dateformat(date)));
		}else if(copynumber==false){
			result="Copynumber Invalid";
			logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Invalid Copynumber.", uID,nISBN,cNUM,dateformat(date)));
		}else{
			if(oloan){
				if(limit && fee){
				Loan loan=new Loan(uID,nISBN,cNUM,date,"0");
				loanList.add(loan);
				result="success";
				logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Success", uID,nISBN,cNUM,dateformat(date)));
				}else if(limit==false){
					result="The Maximun Number of Items is Reached";
					logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:The Maximun Number of Items is Reached.", uID,nISBN,cNUM,dateformat(date)));
				}else if(fee==false){
					result="Outstanding Fee Exists";
					logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Outstanding Fee Exists.", uID,nISBN,cNUM,dateformat(date)));
				}
			}else{
				result="The Item is Not Available";
				logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:The Item is Not Available.", uID,nISBN,cNUM,dateformat(date)));
			}
		}
    	return result;
	}
	
    public boolean lookup(String tISBN, String cNum) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			String ISBN=(loanList.get(i)).getIsbn();
			String copynumber=(loanList.get(i)).getCopynumber();
			if(ISBN.equalsIgnoreCase(tISBN) && copynumber.equalsIgnoreCase(cNum)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			result=false;
		}
		return result;
	}
    
    public boolean checkLimit(int uID) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			int userid=(loanList.get(i)).getUserid();
			if(userid==uID){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag>=Config.MAX_BORROWED_ITEMS){
			result=false;
		}
		return result;
	}
    
    public Object renewal(int uID, String tISBN, String cNum, Date date) {
		String result="";
		int flag=0;
		int index=0;
		boolean limit=LoanTable.getInstance().checkLimit(uID);
		boolean fee=FeeTable.getInstance().lookup(uID);
		for(int i=0;i<loanList.size();i++){
			String ISBN=(loanList.get(i)).getIsbn();
			String copynumber=(loanList.get(i)).getCopynumber();
			int userid=(loanList.get(i)).getUserid();
			if((userid==uID) && ISBN.equalsIgnoreCase(tISBN) && copynumber.equalsIgnoreCase(cNum)){
				flag=flag+1;
				index=i;
			}else{
				flag=flag+0;	
			}
		}
		if(limit && fee){
			if(flag!=0){
				if(loanList.get(index).getRenewstate().equalsIgnoreCase("0")){
					loanList.get(index).setUserid(uID);
					loanList.get(index).setIsbn(tISBN);
					loanList.get(index).setCopynumber(cNum);
					loanList.get(index).setDate(new Date());
					loanList.get(index).setRenewstate("1");
					result="success";
					logger.info(String.format("Operation:Renew Item;Loan Info:[%d,%s,%s,%s];State:Success", uID,tISBN,cNum,dateformat(date)));
				}else{
					result="Renewed Item More Than Once for the Same Loan";
					logger.info(String.format("Operation:Renew Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Renewed Item More Than Once for the Same Loan.", uID,tISBN,cNum,dateformat(date)));
					}
			}else{
				result="The loan does not exist";
				logger.info(String.format("Operation:Renew Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:The loan does not exist.", uID,tISBN,cNum,dateformat(date)));
			}
			
		}else if(limit==false){
			result="The Maximun Number of Items is Reached";
			logger.info(String.format("Operation:Renew Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:The Maximun Number of Items is Reached.", uID,tISBN,cNum,dateformat(date)));
		}else if(fee==false){
			result="Outstanding Fee Exists";
			logger.info(String.format("Operation:Renew Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Outstanding Fee Exists.", uID,tISBN,cNum,dateformat(date)));
		}
		return result;
	}
    
    private String dateformat(Date date){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String datestr=format1.format(date);
		return datestr;
	}
	public Object returnItem(int uID, String tISBN, String cNum, Date date) {
		String result="";
		int flag=0;
		int index=0;
		for(int i=0;i<loanList.size();i++){
			String ISBN=(loanList.get(i)).getIsbn();
			String copynumber=(loanList.get(i)).getCopynumber();
			int userid=(loanList.get(i)).getUserid();
			if((userid==uID) && ISBN.equalsIgnoreCase(tISBN) && copynumber.equalsIgnoreCase(cNum)){
				flag=flag+1;
				index=i;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			long time = date.getTime()-loanList.get(index).getDate().getTime();
			loanList.remove(index);
			logger.info(String.format("Operation:Return Item;Loan Info:[%d,%s,%s,%s];State:Success", uID,tISBN,cNum,dateformat(date)));
			if(time>Config.OVERDUE*Config.STIMULATED_DAY){
				FeeTable.getInstance().applyfee(uID,time);
			}
			result="success";
		}else{
			result="The Loan Does Not Exist";
			logger.info(String.format("Operation:Return Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:The Loan Does Not Exist.", uID,tISBN,cNum,dateformat(date)));
		}
		
		return result;
	}
    
    public List<Loan> getLoanTable() {
		return loanList;
	}
    public boolean looklimit(int uID) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			int userid=(loanList.get(i)).getUserid();
			if(userid==uID){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			result=false;
		}
		return result;
	}
    public boolean checkUser(int uID) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			int userid=(loanList.get(i)).getUserid();
			if(userid==uID){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			result=false;
		}
		return result;
	}
	public boolean checkLoan(String tISBN, String cNum) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			String ISBN=(loanList.get(i)).getIsbn();
			String copynumber=(loanList.get(i)).getCopynumber();
			if(ISBN.equalsIgnoreCase(tISBN) && copynumber.equalsIgnoreCase(cNum)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			result=false;
		}
		return result;
	}
	public boolean checkLoan(String tISBN) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			String ISBN=(loanList.get(i)).getIsbn();
			if(ISBN.equalsIgnoreCase(tISBN)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			result=false;
		}
		return result;
	}
}
