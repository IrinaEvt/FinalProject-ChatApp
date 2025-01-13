package com.chat_project_final.chatprojectfinal.system.db.operations;

import com.chat_project_final.chatprojectfinal.system.db.QueryProcessor;

public class InsertQueryBuilder {
    private QueryProcessor queryProcessor;
    private String tableName;


    public InsertQueryBuilder(QueryProcessor queryProcessor, String tableName) {

        this.tableName      = tableName;
        this.queryProcessor = queryProcessor;
        this.queryProcessor.initNewQueryOperation();
        this.queryProcessor.getQueryBuilder().append("INSERT INTO ").append(tableName);

    }

    public InsertQueryBuilder withValue(String columName, Object value) {


        this.queryProcessor.setQueryColumnValuePair(columName, value);
        return this;
    }

    public boolean insert() {

        String columnDefinition = String.join(",", this.queryProcessor.getColumnCollection());
        String valueDefinition  = String.join(",", this.queryProcessor.getPlaceholderCollection());

        this.queryProcessor.getQueryBuilder().append("(").append(columnDefinition).append(") ")
                .append("VALUES")
                .append("(").append(valueDefinition).append(")");

        int resultCount = this.queryProcessor.processQuery();
        return resultCount > 0;
    }
}
