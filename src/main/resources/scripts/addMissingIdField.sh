#!/bin/bash

echo $(pwd)


#cat <<< $(jq  '.components.schemas.ShoppingItem.properties |= if has("id") then . else .id = {"type":"integer","format":"int64"} end' OpenAPISpec.json) > OpenAPISpec.json

jq  '.components.schemas.ShoppingItem.properties |= if has("id") then . else .id = {"type":"integer","format":"int64"} end' OpenAPISpec.json > OpenAPISpec.json.tmp

if [ -s OpenAPISpec.json.tmp ]; then
   rm OpenAPISpec.json
    mv OpenAPISpec.json.tmp OpenAPISpec.json
    rm -f OpenAPISpec.json.tmp

fi

sed -i -e "s/\*\/\*/application\/json/g" OpenAPISpec.json
rm -f OpenAPISpec.json-e