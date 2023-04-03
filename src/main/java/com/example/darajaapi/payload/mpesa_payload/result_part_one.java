package com.example.darajaapi.payload.mpesa_payload;

public class result_part_one {
    public String ResultType;
    public String ResultCode;
    public String ResultDesc;
    public String OriginatorConversationID;
    public String ConversationID;
    public String TransactionID;
//    public result_reference_data ReferenceData;


    public String getResultType() {
        return ResultType;
    }

    public void setResultType(String resultType) {
        ResultType = resultType;
    }

    public String getResultCode() {
        return ResultCode;
    }

    public void setResultCode(String resultCode) {
        ResultCode = resultCode;
    }

    public String getResultDesc() {
        return ResultDesc;
    }

    public void setResultDesc(String resultDesc) {
        ResultDesc = resultDesc;
    }

    public String getOriginatorConversationID() {
        return OriginatorConversationID;
    }

    public void setOriginatorConversationID(String originatorConversationID) {
        OriginatorConversationID = originatorConversationID;
    }

    public String getConversationID() {
        return ConversationID;
    }

    public void setConversationID(String conversationID) {
        ConversationID = conversationID;
    }

    public String getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(String transactionID) {
        TransactionID = transactionID;
    }
}
