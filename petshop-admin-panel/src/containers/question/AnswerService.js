import request from "../../common/request";

export const getAnswersByQuestionId = (questionId)=>{
    const questionRequest= {
        method: "GET",
        path: "/api/v1/answer/get-all-response-by-question-id",
        params: ["questionId"],
        values: [questionId]
    }
    return request(questionRequest)
}