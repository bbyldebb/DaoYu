const baseUrl = 'https://daoyu-1970945-1312026342.ap-shanghai.run.tcloudbase.com/DaoYu';

const request = function (url, method, data) {
    return uni.request({
        url: baseUrl + url,
        method: method,
        data: data,
    });
};

export { baseUrl, request };