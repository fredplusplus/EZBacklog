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