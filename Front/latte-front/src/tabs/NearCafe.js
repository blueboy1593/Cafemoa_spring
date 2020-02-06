// import React from 'react'

// export default function NearCafe() {
//   function success(pos) {
//     var crd = pos.coords;
  
//     console.log('Your current position is:');
//     console.log('Latitude : ' + crd.latitude);
//     console.log('Longitude: ' + crd.longitude);
//     console.log('More or less ' + crd.accuracy + ' meters.');
//     return crd
//   };

//   const geolocation = () => {
//     console.log(navigator.geolocation.getCurrentPosition(success));
//     return navigator.geolocation.getCurrentPosition(success)
//   }


//   return (
//     <div>
//       <h1>주변 매장 찾는 게시판</h1>
//       {/* {geolocation()} */}
//       {/* {data} */}
//     </div>
//   )
// }





import React, { Component } from 'react'

export default class NearCafe extends Component {
  constructor(props){
    super(props);
    this.state = {
      latitude: 'a',
      longitude: '',
    }
    this.success = this.success.bind(this)
    // 어제 이거 못해가지고 시간 쓴거 생각하면....ㅎ
  }
  // state = {
  //   latitude: '',
  //   longitude: '',
  // }

  success(pos) {
    const crd = pos.coords;
    this.setState({
      latitude: crd.latitude,
      longitude: crd.longitude
    })

    console.log('Your current position is:');
    console.log('Latitude : ' + crd.latitude);
    console.log('Longitude: ' + crd.longitude);
    console.log('More or less ' + crd.accuracy + ' meters.');
    return crd
  }

  geolocation = () => {
    console.log(navigator.geolocation.getCurrentPosition(this.success));
    return
  }

  render() {
    this.geolocation()
    return (
      <div>
        <h1>주변 매장 찾는 게시판</h1>
        <p>현재 위치의</p>
        <p>위도는 {this.state.latitude}</p>
        <p>경도는 {this.state.longitude}</p>
      </div>
    )
  }
}
