export class FAReceivedCreditApp {
    public applicationNumber : string;
	public borrowerName : string;
	public borrowerEmail : string;
	public companyName : string;
	public creditScore : number;
}

export interface CreditAppModel {
     applicationNumber : string;
	 borrowerName : string;
	 borrowerEmail : string;
	 companyName : string;
	 creditScore : number;
}