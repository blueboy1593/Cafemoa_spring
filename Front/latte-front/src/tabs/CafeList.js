import React, { Component } from 'react'
import CafeInfo from '../components/CafeInfo';
import './CafeList.css';
import axios from 'axios';


export default class CafeList extends Component {
    state = {
        cafes: [
            // 이부분은 axios로 받아오더라도 굳이 지우지 말고 주석으로 남겨놓자.!!
            {
                ccid: 1,
                cname: "Twosome Place",
                cpic: "http://www.newsgn.com/imgdata/newsgn_com/201709/2017092554155245.jpg",
            },
            {
                ccid: 2,
                cname: "Starbucks",
                cpic: "https://t1.daumcdn.net/cfile/tistory/236B503C5961BE5536",
            },
            {
                ccid: 3,
                cname: "Coffeebean",
                cpic: "http://www.mirae-biz.com/news/photo/201803/37567_30810_4718.jpg",
            },
            {
                ccid: 4,
                cname: "Banapresso",
                cpic: "http://mblogthumb1.phinf.naver.net/MjAxOTAzMjJfMjM4/MDAxNTUzMjIzNjAwMDU4.jUAy36LFmP88TJk1On4VNol4xYeMPXrEOyMqI83VXOYg.wWNmxoaAUtzUHJ-xSsN48Oif-856lcTlGJCSwBImLE8g.JPEG.lovenonsul/IMG_2529.JPG?type=w800",
            },
            {
                ccid: 5,
                cname: "Idiya Coffee",
                cpic: "http://danmee.chosun.com/site/data/img_dir/2017/08/18/2017081801938_0.jpg",
            },
            {
                ccid: 6,
                cname: "Hollys Coffee",
                cpic: "http://admin.hollys.co.kr/upload/branch/store_201609210824422010.jpg",
            },
        ]
    }

    componentDidMount(){
        const base_url = process.env.REACT_APP_SERVER_IP
        axios.get(base_url + '/cafe/all')
            .then(response =>{
            this.setState({
                cafes:response.data
            });
            });
        };

    render() {
        const {cafes} = this.state;
        // console.log(cafes)
        return (
            <div>
                <br></br>
                <div className="cafes">
                    {cafes.map(cafe => (
                        <CafeInfo
                            key={cafe.ccid}
                            id={cafe.ccid}
                            name={cafe.cname}
                            picture={cafe.cpic}
                            />
                    ))}
                </div>
            </div>
        )
    }
}

