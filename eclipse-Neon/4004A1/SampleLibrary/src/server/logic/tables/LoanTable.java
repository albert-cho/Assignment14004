package server.logic.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import server.logic.model.Loan;
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
}
