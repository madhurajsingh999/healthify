import React, { useEffect, useLayoutEffect } from 'react'
import { useFetchUserInfoMutation } from '../../services/user'

function Dashboard() {
  const [fetchUserInfo,{data,error,isError,isLoading}]=useFetchUserInfoMutation();

  useLayoutEffect(() => {
    fetchUserInfo();
  }, []);

  useEffect(() => {
    if(isError){
      console.log(error);
    }
  },[data,error,isError]);

  return (
    <div>Dashboard {isLoading??<h2>LOADING</h2>}</div>
  )
}

export default Dashboard