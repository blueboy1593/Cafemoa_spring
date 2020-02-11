import React, {Component} from 'react';
import queryString from "query-string";
import axios from 'axios';

const Test=({location})=>{

        console.log(location);
        //이 컴포넌트로 오면서 ?code=???? 를 달고 옴. 주소는 http://localhost:3000/visitor/test
       
        // query string만 파싱하기
        const query = queryString.parse(location.search);
        console.log(query.code);
        // const ttt = query.code
        // const decoded_token = jwtDecode(ttt)
        // console.log(decoded_token)

        const base_url = process.env.REACT_APP_SERVER_IP
        
        
        
        // axios({
        //     method: 'get',
        //     url: base_url+'/user/kakaologin',
        //     data: {
        //       code: query.code
        //     }
        //   }).then(response => {
        //     console.log(response)
        // }) 
        // .catch(error => {
        //     console.log('error')
        //     console.error(error)
        // })
        // console.log(base_url);

        const codeJson={
            code : query.code
        }
        axios.get(base_url + '/user/kakaologin',codeJson)
        .then(response => {
            console.log(response)
        }) 
        .catch(error => {
            console.log('error')
            console.error(error)
        })


        return <div> 잠시만요 </div>
    
}

export default Test;