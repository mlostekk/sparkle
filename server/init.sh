#!/usr/bin/env bash
SERVER_URL=http://127.0.0.1:5984

echo "creating questions table ..."
curl -X PUT $SERVER_URL/questions

echo "populating questions table ..."
curl -H "Content-type:application/json" -d @data_questions.json -X POST $SERVER_URL/questions/_bulk_docs

echo "creating questions 'by category' view"
curl -H "Content-type:application/json" -d @data_questions_view.json -X PUT $SERVER_URL/questions/_design/questions

echo "creating questions table ..."
curl -X PUT $SERVER_URL/categories

echo "pupulating categories table ..."
curl -H "Content-type:application/json" -d @data_categories.json -X POST $SERVER_URL/categories/_bulk_docs

echo "creating answers table ..."
curl -X PUT $SERVER_URL/answers
