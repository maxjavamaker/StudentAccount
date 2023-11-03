public class StudentAccount{
	public static double balance = 46000;
	public static double totalLoan = 0;
	public static void main(String[] args){
		System.out.println("Your opening balance is: $46000.0");
		if(args.length == 0){
			System.out.println("Missing amount");
		}
		for(int i = 0; i < args.length; i += 2){
			if(!(i + 1 < args.length)){
				System.out.println("Missing amount");
				System.out.println("Your closing balance is $" + balance);
					return;
			}
			if(!isValidOperation(args[i]) == true){
				System.out.println("Invalid Operation: " + args[i]);
					System.out.println("Your closing balance is $" + balance);
					return;
			}
			String command = args[i];
			switch(command){
				case "activityFee":
					addActivityFee(args[i + 1]);
					break;
				case "scholarship":
					applyScholarship(args[i+1]);
					break;
				case "pay":
					submitPayment(args[i + 1]);
					break;
				case "borrow":
					takeLoan(args[i + 1]);
					break;
				case "paybackLoan":
					paybackLoan(args[i + 1]);
					break;
			}
		}
		System.out.println("Your closing balance is $" + balance);
	}

	public static boolean addActivityFee(String amount){
		if(getValidDollarAmount(amount) == -1){
			System.out.println("Invalid fee amount: " + amount);
			return false;
		}
		double fee = getValidDollarAmount(amount);
		balance += fee;
		System.out.println("Activity fee applied. Your balance is now: $" + balance);
		return true;
	}

	public static boolean applyScholarship(String amount){
		if(getValidDollarAmount(amount) == -1){
			System.out.println("Invalid scholarship amount: " + amount);
			return false;
		}
		double scholarship = getValidDollarAmount(amount);
		if(scholarship > balance){
			System.out.println("Scholarship rejected: scholarship can't exceed current balance. Student's current balance is $" + balance);
			return false;
		}
		else{
			balance -= scholarship;
			System.out.println("Scholarship for $" + scholarship + " has been applied to your balance. Your current balance is now $" + balance);
			return true;
		}
	}

	public static double submitPayment(String amount){
		if(getValidDollarAmount(amount) == -1){
			System.out.println("Invalid payment amount: " + amount);
			return -1;
		}
		double originalBalance = balance;
		double payment = getValidDollarAmount(amount);
		if(payment >= balance){
			balance = 0;
			System.out.println("Balance is now zero");
			return (payment - originalBalance);
		}
		else{
			balance -= payment;
			return balance;
		}
	}

	public static double takeLoan(String amount){
		if(getValidDollarAmount(amount) == -1){
			System.out.println("Invalid loan amount: " + amount);
			return -1;
		}
		double loan = getValidDollarAmount(amount);
		if((totalLoan + loan) > 10000){
			System.out.println("Loan application rejected: total loans may not exceed $10,000, and you have already borrowed $" + totalLoan);
			return -1;
		}
		else if(loan > balance){
			System.out.println("Loan application rejected: loan can't exceed current balance. Student's current balance is $" + balance);
			return -1;
		}
		else{
			balance -= loan;
			totalLoan += loan;
			return balance;
		}
	}

	public static double paybackLoan(String amount){
		double originalLoan = totalLoan;
		if(getValidDollarAmount(amount) == -1){
			System.out.println("Invalid payback amount: " + amount);
			return -1;
		}
		double payloan = getValidDollarAmount(amount);
		if(totalLoan == 0){
			System.out.println("No outstanding Loan");
			return -1;
		}
		else if(payloan >= totalLoan){
			totalLoan = 0;
			System.out.println("Loans fully paid off");
			return(payloan - originalLoan);
		}
		else{
			totalLoan -= payloan;
			return totalLoan;
		}
	}

	public static double getAccountBalance(){
		return balance;
	}

	public static void setAccountBalance(double value){
	}

	public static double getLoansTotal(){
		return totalLoan;
	}

	public static double getValidDollarAmount(String str){
		if(str.startsWith("$")){
			String modifiedStr = str.substring(1);
			if (modifiedStr.matches("\\d+(\\.\\d{1,2})?")){
				return Double.parseDouble(modifiedStr);
			}

			else{
				return -1;
			}
		}
		else{
			return -1;
		}
	}

	public static boolean isValidOperation(String operator){
		if("pay".equals(operator) || "paybackLoan".equals(operator) || "borrow".equals(operator) || "scholarship".equals(operator) || "activityFee".equals(operator)){
			return true;
		}
		else{
			return false;
		}
	}
}
