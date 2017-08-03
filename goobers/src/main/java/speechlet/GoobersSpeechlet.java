package main.java.speechlet;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import main.java.db.AccountDAO;
import main.java.model.User;
import main.java.perms.View;
import main.java.resource.Account;

/**
 * This is just an example to help us figure out what we need in the model
 * @author bree
 *
 */
public class GoobersSpeechlet implements Speechlet {

	private static final String SESSION_STAGE = "stage";
	private static final String USERNAME = "username";
	private static final String PASSPHRASE = "passphrase";
	private static final String AMOUNT = "amount";

	private static final int LOGIN = 1;
	private static final int USERLOGIN = 2;
	private static final int PASSLOGIN = 3;
	private static final int BILL_PAY = 4;
	private static final int TRANSFER = 5;
	private static final Object VIEW_BALANCE = null;

	//	vars needed for this session
	private User currentUser;
	private AccountDAO accountDao;

	@Override
	public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
		accountDao = new AccountDAO();
	}


	@Override
	public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
		return accountLoginStart(session);
	}


	private SpeechletResponse accountLoginStart(Session session) {
		String speechOutput = "";

		// Reprompt speech will be triggered if the user doesn't respond.
		String repromptText = "Welcome to the Goober app. Please say your first name.";

		// The stage variable tracks the phase of the dialogue.
		// When this function completes, it will be on stage 1.
		session.setAttribute(SESSION_STAGE, LOGIN);
		speechOutput = repromptText;

		return newAskResponseLocal(speechOutput, repromptText);

	}


	@Override
	public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
		Intent intent = request.getIntent();
		String intentName = (intent != null) ? intent.getName() : null;

		System.err.println("INTENT NAME: " + intentName);
		if ("credentialIntent".equals(intentName)) {
			if((Integer) session.getAttribute(SESSION_STAGE) == LOGIN) {
//	        	set the name as session variable
	        	Slot nameSlot = intent.getSlot("Credential");
	        	String username = nameSlot.getValue();
	        	session.setAttribute(USERNAME, username);
	        	return handleNameIntent(session);
			} else {
	        	// store passphrase as a session variable
	        	Slot passSlot = intent.getSlot("Credential");
	        	String phrase = passSlot.getValue();
	        	session.setAttribute(PASSPHRASE, phrase);
	        	return handlePassphraseIntent(session);
			}
        } else if("amountIntent".equals(intentName)) {
        	Slot amountSlot = intent.getSlot("Amount");
        	String amountString = amountSlot.getValue();
        	session.setAttribute(AMOUNT, amountString);
        	if((Integer) session.getAttribute(SESSION_STAGE) == BILL_PAY) {
        		return handleBillPayExecute(session);
        	} else {
        		return handleTransferMoneyExecute(session);
        	}
        }
        else if ("viewAccountIntent".equals(intentName)) {
            return handleViewAccountIntent(session);
        } else if ("viewInsuranceIntent".equals(intentName)) {
            return handleViewInsuranceIntent(session);
        } else if ("transferMoneyIntent".equals(intentName)) {
            return handleTransferMoneyIntent(session);
        } else if ("payBillIntent".equals(intentName)) {
            return handlePayBillIntent(session);
        } else if ("updateInsuranceIntent".equals(intentName)) {
            return handleUpdateInsuranceIntent(session);
        } else if ("removeAccountIntent".equals(intentName)) {
            return handleRemoveAccountIntent(session);
        } else if ("addAccountIntent".equals(intentName)) {
            return handleAddAccountIntent(session);
        } else if ("AMAZON.StopIntent".equals(intentName)) {
            return handleStopIntent(session);
        } else if ("AMAZON.CancelIntent".equals(intentName)) {
            return handleCancelIntent(session);
        } else if ("menuIntent".equals(intentName)) {
            return handleMenuIntent(session);
        } else if("logoutIntent".equals(intentName)) {
        	return handleLogoutIntent(session);
        } else if ("AMAZON.HelpIntent".equals(intentName)) {
            String speechOutput = "";
            int stage = -1;
            if (session.getAttributes().containsKey(SESSION_STAGE)) {
                stage = (Integer) session.getAttribute(SESSION_STAGE);
            }
//          TODO update these
            switch (stage) {
                case 0:
                    speechOutput =
                            "Knock knock jokes are a fun call and response type of joke. "
                                    + "To start the joke, just ask by saying tell me a"
                                    + " joke, or you can say exit.";
                    break;
                case 1:
                    speechOutput = "You can ask, who's there, or you can say exit.";
                    break;
                case 2:
                    speechOutput = "You can ask, who, or you can say exit.";
                    break;
                default:
                    speechOutput =
                            "Knock knock jokes are a fun call and response type of joke. "
                                    + "To start the joke, just ask by saying tell me a "
                                    + "joke, or you can say exit.";
            }
            String repromptText = speechOutput;
            return newAskResponseLocal(speechOutput, repromptText);
        } else {
            throw new SpeechletException("Invalid Intent");
        }
	}


	private SpeechletResponse handleTransferMoneyExecute(Session session) {
		// TODO Auto-generated method stub
		return null;
	}


	private SpeechletResponse handleBillPayExecute(Session session) {

		int amount = Integer.parseInt(session.getAttribute(AMOUNT).toString());
		accountDao.setAccountBalance("checking", (float) amount);
		
		float accountBalance = accountDao.getAccountBalance("checking");
		
		String speechOutput = "Your bill has been paid. You now have " + accountBalance + " in your checking account.";
		String repromptText = "What would you like to do with your account?";
		
		return newAskResponseLocal(speechOutput, repromptText);
	}


	private SpeechletResponse handlePassphraseIntent(Session session) {
		String speechOutput = "";
		String repromptText = "";
//		check that passphrase/pin matches
		session.setAttribute(SESSION_STAGE, PASSLOGIN);
		
		if(session.getAttribute(USERNAME) == null) {
			speechOutput = "Please say your username";
			repromptText = speechOutput;
		} else {

			User tempUser = accountDao.getUserFromPin(session.getAttribute(USERNAME).toString(), session.getAttribute(PASSPHRASE).toString());
			if(tempUser == null) {
				tempUser = accountDao.getUserFromPassphrase(session.getAttribute(USERNAME).toString(), session.getAttribute(PASSPHRASE).toString());
			}
	
			if(tempUser != null) {
	//			initialize currentUser
				boolean isOwner = false;
	
				if(tempUser.isOwner()) {
	//				check if the passphrase matches the pin or the passphrase
					if(session.getAttribute(PASSPHRASE).equals(tempUser.getPin())) {
						currentUser = tempUser;
					} else {
						currentUser = new User(tempUser.getUserName(), tempUser.getPassPhrase(), tempUser.getPin(), new View(), tempUser.isOwner());
					}
				} else {
					currentUser = tempUser;
				}
	
				speechOutput = "What would you like to do with your account?";
				repromptText = speechOutput;
			} else {
				session.setAttribute(USERNAME, null);
				session.setAttribute(PASSPHRASE, null);
				session.setAttribute(SESSION_STAGE, LOGIN);
				
	//			else reprompt for correct pin/passphrase
				speechOutput = "Your username and passphrase were incorrect. Please say your first name.";
				repromptText = "Please state your username";
	
			}
		}

		return newAskResponseLocal(speechOutput, repromptText);
	}

	private SpeechletResponse handleNameIntent(Session session) {

//		TODO refine phrasing?
		session.setAttribute(SESSION_STAGE, USERLOGIN);
		String speechOutput = "Please say your pin or passphrase";

		// Reprompt speech will be triggered if the user doesn't respond.
		String repromptText = speechOutput;

		return newAskResponseLocal(speechOutput, repromptText);
	}


	private SpeechletResponse handleUpdateInsuranceYesIntent(Session session) {
		// TODO Auto-generated method stub
		return null;
	}


	private SpeechletResponse handleMenuIntent(Session session) {
		if(currentUser != null) {

		} else {
//			TODO redirect them back to the login state
		}

		return null;
	}


	private SpeechletResponse handleCancelIntent(Session session) {
		PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
		outputSpeech.setText("Goodbye");

		return SpeechletResponse.newTellResponse(outputSpeech);
	}

	//	TODO save data
	private SpeechletResponse handleStopIntent(Session session) {
		PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
		outputSpeech.setText("Goodbye");

		return SpeechletResponse.newTellResponse(outputSpeech);
	}


	private SpeechletResponse handleAddAccountIntent(Session session) {

		if(currentUser != null) {
			// TODO check if the currentUser has permission

		} else {

		}


		return null;
	}


	private SpeechletResponse handleRemoveAccountIntent(Session session) {
		// TODO Auto-generated method stub
		return null;
	}


	private SpeechletResponse handleUpdateInsuranceIntent(Session session) {
		// TODO Auto-generated method stub
		return null;
	}


	private SpeechletResponse handlePayBillIntent(Session session) {

		String speechOutput = "";
		String repromptText = "";

		// check if user is logged in
		if(currentUser != null) {
			// check if user has permissions
			if(currentUser.getAccess().canBillPay()) {
				session.setAttribute(SESSION_STAGE, BILL_PAY);
				speechOutput = "What amount would you like to pay?";
				repromptText = speechOutput;
			} else {
				speechOutput = "You do not have permission to pay bills. What would you like to do with your account?";
				repromptText = "What would you like to do with your account?";
			}
		} else {
			speechOutput = "You must log in first. What is your username?";
			repromptText = "What is your username?";
		}

		return newAskResponseLocal(speechOutput, repromptText);
	}


	private SpeechletResponse handleTransferMoneyIntent(Session session) {
		
		String speechOutput = "";
		String repromptText = "";

		// check if user is logged in
		if(currentUser != null) {
			// check if user has permissions
			if(currentUser.getAccess().canBillPay()) {
				session.setAttribute(SESSION_STAGE, TRANSFER);
				speechOutput = "What amount would you like to transfer?";
				repromptText = speechOutput;
			} else {
				speechOutput = "You do not have permission to transfer money. What would you like to do with your account?";
				repromptText = "What would you like to do with your account?";
			}
		} else {
			speechOutput = "You must log in first. What is your username?";
			repromptText = "What is your username?";
		}

		return newAskResponseLocal(speechOutput, repromptText);
	}


	private SpeechletResponse handleViewInsuranceIntent(Session session) {
		// TODO Auto-generated method stub
		return null;
	}


	private SpeechletResponse handleViewAccountIntent(Session session) {
		String speechOutput = "";
		String repromptText = "";

		// check if user is logged in
		if(currentUser != null) {
			// check if user has permissions
			if(currentUser.getAccess().canViewBankBalance()) {
				session.setAttribute(SESSION_STAGE, VIEW_BALANCE);
				Float balance = accountDao.getAccountBalance("checking");
				speechOutput = "Your account balance is " + balance;
				repromptText = "What would you like to do with your account?";
			} else {
				speechOutput = "You do not have permission to view this account. What would you like to do with your account?";
				repromptText = "What would you like to do with your account?";
			}
		} else {
			speechOutput = "You must log in first. What is your first name?";
			repromptText = "What is your first name?";
		}

		return newAskResponseLocal(speechOutput, repromptText);
	}
	
	private SpeechletResponse handleLogoutIntent(Session session) {
		currentUser = null;
		
		session.setAttribute(SESSION_STAGE, LOGIN);
		
		String speechOutput = "You have successfully logged out. Please state your first name.";
		String repromptText = "Please state your first name.";
		
		return newAskResponseLocal(speechOutput, repromptText);
	}

	private SpeechletResponse newAskResponseLocal(String speechOutput, String repromptText) {
		OutputSpeech outputSpeech, repromptOutputSpeech;
		outputSpeech = new PlainTextOutputSpeech();
		repromptOutputSpeech = new PlainTextOutputSpeech();
		((PlainTextOutputSpeech) outputSpeech).setText(speechOutput);
		((PlainTextOutputSpeech) repromptOutputSpeech).setText(repromptText);
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(repromptOutputSpeech);
		return SpeechletResponse.newAskResponse(outputSpeech, reprompt);
	}


	@Override
	public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {

	}

}
