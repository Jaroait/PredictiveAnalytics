

//Top Level
public class ReorderingSupplies{
	SendEmail sendEmail;
	
	//Fix size later
	boolean[] posOrNeg;
	String[] items;
	int[] lower;
	int[] upper;
	
	
	//Fake RFID numbers (Good/good/bad/good)
	int[] rfid = {118, 236, 112, 32};
	
	//TODO: NEED TO CONNECT ANALYSIS AND RFID
	
	public void initializeArray(){
		PredictiveAnalytics prediction = new PredictiveAnalytics();
		lower = prediction.lowerBounds;
		upper = prediction.upperBounds;
		items = prediction.items;		
		posOrNeg = new boolean[items.length];
				
		for(int i = 0; i < items.length; i++){
			if(lower[i] <= rfid[i] && upper[i]>= rfid[i]){
				//Good!
				posOrNeg[i] = true;
			}
			else{
				posOrNeg[i] = false;
			}
		}
	}
	
	public void initializeEmail() {
		sendEmail.cleanPrint(items, rfid, posOrNeg, lower, upper);
	}
	
	public void sendEmail() {
		sendEmail.sendEmailCall();
	}
	
	public static void main(String[] args) {
		ReorderingSupplies reorder = new ReorderingSupplies();
		reorder.sendEmail = new SendEmail();
		reorder.initializeArray();
		reorder.initializeEmail();
		reorder.sendEmail();
	}
}