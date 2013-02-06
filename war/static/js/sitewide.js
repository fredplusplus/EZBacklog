_.templateSettings = {
	interpolate : /\<\@\=(.+?)\@\>/gim,
	evaluate : /\<\@([\s\S]+?)\@\>/gim,
	escape : /\<\@\-(.+?)\@\>/gim
};

function formatDate(date) {
	var time = new Date(date + " UTC");
	return (time.getMonth() + 1) + '/' + time.getDate() + '/'
			+ time.getFullYear();
}

function formatDateAccurate(date) {
	var time = new Date(date + " UTC");
	var hour = time.getHours();
	var minute = time.getMinutes();
	var second = time.getSeconds();
	if (hour < 10) {
		hour = "0" + hour;
	}
	if (minute < 10) {
		minute = "0" + minute;
	}
	if (second < 10) {
		second = "0" + second;
	}
	return (time.getMonth() + 1) + '/' + time.getDate() + '/'
			+ time.getFullYear() + ' ' + hour + ":" + minute + ":" + second;
}