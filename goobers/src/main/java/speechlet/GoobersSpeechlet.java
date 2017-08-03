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
import com.amazon.speech.speechlet.User;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;


import main.java.db.AccountDAO;
import main.java.resource.Account;

/**
 * This is just an example to help us figure out what we need in the main.java.model
 * @author bree
 *
 */
public class GoobersSpeechlet implements Speechlet {
	
	private static final String SESSION_STAGE = "stage";
	private static final Object LOGIN = 1;
	
//	TODO make these session vars
	private Account currentAccount;
	private User currentUser;
	private String name;
	private AccountDAO accountDao;

	@Override
	public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
		
	}


	@Override
	public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
		return accountLoginStart(session);
	}


	private SpeechletResponse accountLoginStart(Session session) {
		String speechOutput = "";

        // Reprompt speech will be triggered if the user doesn't respond.
        String repromptText = "What is your name?";

        // The stage variable tracks the phase of the dialogue.
        // When this function completes, it will be on stage 1.
        session.setAttribute(SESSION_STAGE, LOGIN);
//        speechOutput = "What is your name?";
        speechOutput = repromptText;
       
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
	public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("firstNameIntent".equals(intentName)) {
//        	TODO set the name as private instance var or session variable
        	Slot nameSlot = intent.getSlot("FirstName");
        	name = nameSlot.getValue();
        	return handleNameIntent(session);
        } else if("passphraseIntent".equals(intentName)) {
//        	TODO store passphrase as a session variable
        	Slot passSlot = intent.getSlot("");
        	return handlePassphraseItent(session);
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
        } else if ("AMAZON.HelpIntent".equals(intentName)) {
            String speechOutput = "";
            int stage = -1;
            if (session.getAttributes().containsKey(SESSION_STAGE)) {
                stage = (Integer) session.getAttribute(SESSION_STAGE);
            }
//            TODO update these
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
            OutputSpeech outputSpeech, repromptOutputSpeech;
            outputSpeech = new PlainTextOutputSpeech();
            repromptOutputSpeech = new PlainTextOutputSpeech();
            ((PlainTextOutputSpeech) outputSpeech).setText(speechOutput);
            ((PlainTextOutputSpeech) repromptOutputSpeech).setText(repromptText);
            Reprompt reprompt = new Reprompt();
            reprompt.setOutputSpeech(repromptOutputSpeech);
            return SpeechletResponse.newAskResponse(outputSpeech, reprompt);
        } else {
            throw new SpeechletException("Invalid Intent");
        }
	}


	private SpeechletResponse handlePassphraseItent(Session session) {
//		TODO check that passphrase/pin matches
//			 --> query the main.java.db
		
//		TODO - initialize currentUser
//		if passphrase with account owner, set permissions to View
//		currentUser = new User()...
//		else if pin with account owner, set permissions to Manage
//		else if passphrase with account user, set permissions to View
		
		
//		else reprompt for correct pin/passphrase
		
		return null;
	}

//	TODO decide if we want to handle bad user names, or wait until we 
//	have a bad username/password
	private SpeechletResponse handleNameIntent(Session session) {
		
//		TODO check if name belongs to a user
//			 --> query the main.java.db to see if the account contains that user
		
//		TODO if name is the account owner, ask for pin or passphrase
//			 else if account user ask for passphrase
//			 else reprompt for correct user name
		
		return null;
	}


	private SpeechletResponse handleUpdateInsuranceYesIntent(Session session) {
		// TODO Auto-generated method stub
		return null;
	}


	private SpeechletResponse handleMenuIntent(Session session) {
		if(currentUser != null) {
			
		} else {
//			TODO put them back in the login state
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}


	private SpeechletResponse handleTransferMoneyIntent(Session session) {
		// TODO Auto-generated method stub
		return null;
	}


	private SpeechletResponse handleViewInsuranceIntent(Session session) {
		// TODO Auto-generated method stub
		return null;
	}


	private SpeechletResponse handleViewAccountIntent(Session session) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
		
	}
	
}
