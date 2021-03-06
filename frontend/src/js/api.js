import { request } from './request';

// ==============USER===================
const login = function (userID,nickName,avatarImg) {
	return request('/login', 'GET',{
		userID: userID,
		nickName: nickName,
		avatarImg: avatarImg
	});
}; 

const getUserInfo = function (userID) {
	return request('/getUserInfo', 'GET',{
		userID:userID
	});
}; 

const modifyUserInfo = function (userID,gender,birthday,phoneNum,address,introduction) {
	return request('/modifyUserInfo', 'GET',{
		userID:userID,
		gender:gender,
		birthday:birthday,
		phoneNum:phoneNum,
		address:address,
		introduction:introduction
	});
}; 

// ==============POST===================

const getAllPost = function () {
	return request('/getAllPost', 'GET');
};

const getAllMyPost = function (userID) {
	return request('/getAllMyPost', 'GET',{
		userID:userID
	});
}; 

const getAllMyFollowPost = function (userID) {
	return request('/getAllMyFollowPost', 'GET',{
		userID:userID
	});
}; 

const getPostDetailWithComments = function (postID) {
	return request('/getPostDetailWithComments', 'GET',{
		postID:postID
	});
}; 

const followPost = function (userID,postID) {
	return request('/followPost', 'GET',{
		userID:userID,
		postID:postID
	});
}; 

const unfollowPost = function (userID,postID) {
	return request('/unfollowPost', 'GET',{
		userID:userID,
		postID:postID
	});
}; 

const setPostStatusZero = function (postID) {
	return request('/setPostStatusZero', 'GET',{
		postID:postID
	});
}; 

const publishPost = function (userID,content,image,address,tags,status) {
	return request('/publishPost', 'GET',{
		userID:userID,
		content:content,
		image:image,
		address:address,
		tags:tags,
		status:status
	});
}; 

// ==============COMMENT===================

const addComment = function (userID,postID,content) {
	return request('/addComment', 'GET',{
		userID:userID,
		postID:postID,
		content:content
	});
}; 

// ==============TAG===================

const addTag = function (tagName) {
	return request('/addTag', 'GET',{
		tagName:tagName
	});
}; 

const getAllTags = function () {
	return request('/getAllTags', 'GET');
}; 

// ==============SEARCH===================

const searchPost = function (keyword) {
	return request('/searchPost', 'GET',{
		keyword:keyword
	});
}; 

export {
	login,getUserInfo,modifyUserInfo,
	getAllPost,getAllMyPost,getAllMyFollowPost,getPostDetailWithComments,followPost,unfollowPost,setPostStatusZero,publishPost,
	addComment,addTag,getAllTags,
	searchPost
};