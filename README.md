# emailGeneratorAI_Backend
This is an AI powered email reply generator SpringBoot Application using gemini AI

This Application will generate the replies to the email that you give to it and you can give tone also if you need and it will generate the replies by calling Gemini AI API

To Run the Application we need:

1) Java 25
2) Provide your google gemini key in the environment variables (GEMINI_URL , GEMINI_KEY) in the environment configuration to use Gemini AI API
   GEMINI_URL=GEMINI_URL=https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key\=
   GEMINI_KEY=[your key]

For Key login here https://aistudio.google.com/api-keys

API: http://localhost:8080/api/v1/email/generate

sample request:

{
    "emailContent":"Hi Ram it is krishna, can you pls share me the update about the project",
    "tone": "friendly"
}

sample response:

Hi Krishna,

Thank you for your inquiry regarding the project update.

I'm pleased to report that the project is currently [on schedule/progressing well/in the final stages]. We have successfully completed [mention a key milestone or phase, e.g., the development phase/initial testing] and are now focused on [mention current key activity, e.g., user acceptance testing/final implementation]. We anticipate reaching our next major milestone, [mention milestone], by [date/end of the week].

Please let me know if you require any specific details or further information.

Best regards,

Ram


