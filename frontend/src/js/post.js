import { request } from './request';

const getAllPost = function () {
	return request('/getAllPost', 'GET');
};

export {
	getAllPost
};