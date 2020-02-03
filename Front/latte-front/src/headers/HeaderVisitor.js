import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import store from '../store';

const logo_style = {
    opacity: 1,
    display: 'block'
}

// const space_style = {
//     opacity: 0,
//     display: 'block'
// }

class HeaderVisitor extends Component {
    state = store.getState()
    render() {
        return (
            <div className="top-fixed">
                <div id="logo" className="logo" style={logo_style}><Link to='/'><img src="/img/logo.png" width="120px" alt="라떼는말이야" /></Link>
                    <ul className="icon-list" style={{float: "right"}}>
                        <Link to='/visitor/story'><img src="/img/story.png" alt="우리의이야기" /></Link>
                        <Link to='/'><img src="/img/cafeinfo.png" alt="카페정보" /></Link>
                        <Link to='/visitor/lookaround'><img src="/img/lookaround.png" alt="둘러보기" /></Link>
                        <Link to='/visitor/customerCenter'><img src="/img/customercenter.png" alt="고객센터" /></Link>
                    </ul>
                </div>
            </div>
        );
    }
}

export default HeaderVisitor;