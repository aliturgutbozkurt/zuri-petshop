import React, { useEffect, useState } from "react";
import QuestionTable from "./QuestionTable"
import { getAllQuestions } from "./QuestionService"
export default function QuestionList() {

  const columns = [
    "id", "Soran Kişi", "Soru Açıklaması", "Detay"
  ]
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(5);
  const [count, setCount] = useState(0);
  const [questions,setQuestions] = useState();  


  useEffect(() => {
    getAllQuestions(page, size).then(response => {
      setQuestions(response.data.content);
    }).catch(e => {
      console.log(e);
    });
  }, []);

  useEffect(() => {
    getAllQuestions(page, size).then(response => {
      setQuestions(response.data);
    }).catch(e => {
      console.log(e);
    });
  }, [page,size]);

  const handlePageChange = page => {
    setPage(page);
  }
  const handleRowsPerPageChange = rowsPerPage => {
    setSize(rowsPerPage);
  }


  return (

    <>
      <QuestionTable
        rows={questions}
        handlePageChange={handlePageChange}
        handleRowsPerPageChange={handleRowsPerPageChange}
        columns={columns}
        hiddenIndexes={[3, 4, 5]}
        isOperation={true}
      />
    </>
  );
}