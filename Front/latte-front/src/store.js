import {createStore} from 'redux';
// import {} from 'react-router-dom';

export default createStore(function(state, action){
    console.log('store에 접근')
    if(state === undefined){
        return {isLoggedIn: false,
            id: '',
            pass: '',
            name:'',
            sajang: false
        }
    }
    if (action.type === 'LOGIN') {
        if (state.id === action.info.id) {
            if (state.pass === action.info.pass){
                if (state.sajang === true){
                    console.log('store를 통해 로그인 성공!')
                    // alert('아이고 와주셔서 감사합니다 사장님')
                    return {...state, isLoggedIn:true}
                }
                console.log('store를 통해 로그인 성공!')
                // alert('환영합니다 손님. 주문하십쇼')
                return {...state, isLoggedIn:true}
            }
            console.log('store를 통해 로그인 실패!')
            alert('비밀번호가 틀립니다.')
            return {...state, isLoggedIn:false}
        }
        console.log('store를 통해 로그인 실패!')
        alert('아이디가 존재하지 않습니다.')
        return {...state, isLoggedIn:false}
    }
    if (action.type === 'SIGNUP') {
        if (action.info.sajang === true) {
            console.log('사장님. 회원가입된 아이디와 비번은', action.info, '입니다')
            return {...state, id: action.info.id, pass: action.info.pass, sajang: true}        
        }
        console.log('손님! 회원가입된 아이디와 비번은', action.info, '입니다')
        return {...state, id: action.info.id, pass: action.info.pass, name: action.info.name, sajang:false}
    }
    return state;
}, window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
)