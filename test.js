// test.js
// Need to wait for the page to load to access the newly created
// page elements

window.onload = function(){
	// sleep taken from here
	// http://stackoverflow.com/questions/951021/what-is-the-javascript-version-of-sleep
	function sleep(ms) {
		return new Promise(resolve => setTimeout(resolve, ms));
	}

	async function animate(p) {
		var	frame_width = 64;
		var n_frames = 8;

		while(true){
			var i;
			var position;
			var position_str;
			for(i=0; i<n_frames; i++) {
				await sleep(250);
				position = (n_frames*frame_width)-(i*frame_width);
				position_str = position.toString().concat("px")
				p.style.backgroundPositionX = position_str
			}
		//	await sleep(1000);
		}
	}

	var person = document.getElementById("ani_frame");

	animate(person);
}
