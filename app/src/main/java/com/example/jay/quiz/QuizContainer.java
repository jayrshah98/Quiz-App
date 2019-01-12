package com.example.jay.quiz;

public final class QuizContainer {

    private QuizContainer()
    {

    }
    public static class QuestionsTable   {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String Column_ID = "id";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
    }

    public static class User {
        public static final String TABLE_NAME = "UserTable";
        public static final String Table_Column_ID = "id";
        public static final String Table_Column_1_Name = "name";
        public static final String Table_Column_2_Email = "email";
        public static final String Table_Column_3_Password = "password";
    }
}
