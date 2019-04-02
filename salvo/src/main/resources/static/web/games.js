//================ VUE VAR DECLARATION ===================

var myVue = new Vue({
	el: "#app",
	data: {
		gamesJson: {},
		sortedLeaderboard: [],
		existingUserInput: {userName: "", password: ""},
		newUserInput: {firstName: "", lastName: "", userName: "", password: ""},
		logInVisible: true,
		auth: false
	},
	methods: {
		gameOutcome: function(game){
			if(game.scores.length == 1){
				return "Not started"
			}			
			else if(game.scores[0].score == null || game.scores[1].score == null){
				return "Not finished"
			}
			else if(game.scores[0].score.points == 1){
				return "Player-1"
			}
			else if(game.scores[1].score.points == 1){
				return "Player-2"
			}			
			else{
				return "Tied"
			}
	},		
		toggle: function(){
			this.logInVisible = !this.logInVisible;
			//console.log("logInVisible: " + this.logInVisible);
		},		
		getGamesInfo: function(){
				fetch('/api/games', {
				method: 'GET'
			})
			.then(function (response) {
					if (!response.ok) {
        	throw new Error("HTTP error, status = " + response.status);
      		}
					return response.json();
					//alert("Game Data Status: " + response.status)
			})
			.then(function (gamesJson) {
				//console.log(gamesJson);
				//console.log("Second .then() works!");
				myVue.gamesJson = gamesJson;
					if(myVue.gamesJson.logged_player !== 'guest'){
						myVue.auth = true
					}					
			}).catch(function (error) {
				console.log("The getGamesInfo fetching(GET) to api/games catches an error");
				alert(error);
				//console.log("Error during fetch" + error.message)
			});
		},
		getLeaderBoardInfo: function(){
				fetch('/api/leaderboard', {
				method: 'GET'
			})
			.then(function (response) {
					if (!response.ok) {
        	throw new Error("HTTP error, status = " + response.status);
      		}
					return response.json();
					//alert("Leaderboard Data Status" + response.status);
			})
			.then(function (leaderboard) {
					console.log(leaderboard);
					//myVue.sortedLeaderboard = leaderboard.sort((a,b) => b.total_points - a.total_points);
					
					myVue.sortedLeaderboard = leaderboard.sort(function(a,b){
						if(b.total_points - a.total_points != 0){
							return b.total_points - a.total_points
						}else if(b.total_played - a.total_played != 0){
							return a.total_played - b.total_played
						}else{
							return b.won.length - a.won.length
						}
					});				
			}).catch(function (error) {
				alert(error);
				//console.log("Error during fetch" + error.message)
			});
		},		
		logInUser: function(arg){
			if(arg.userName == "" || arg.password == ""){
				alert("Please enter your email and password");
			}
			else{
			fetch('/api/login', {
        credentials: 'include',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        method: 'POST',
        body: getBody(arg)
    })
    .then(function (data) {
				if (!data.ok) {
        throw new Error("HTTP error, status = " + data.status);
      	}
				console.log("Log In Status: " + data.status);
				myVue.auth = true;				
				//we want to refresh the page:			
				document.location.reload();
    })
    .catch(function (error) {
        console.log('Request failure: ', error);
    });
			}
		},		
		signUpUser: function(){
			
			//first fetch is for creating the new user
			fetch('/api/players', {
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(myVue.newUserInput)
				//remember: the body in any request must be a string
    })
    .then(function (data) {
				if (!data.ok) {
        throw new Error("HTTP error, status = " + data.status);
      	}
        console.log('Fetch succeeded and with server response: ', data);
				console.log("Sign Up Status: " + data.status);				
				//second fetch is for authentication of the this new user
				myVue.logInUser(myVue.newUserInput);
				//window.location.reload();
    })
    .catch(function (error) {
        console.log(error);
    });			
	},		
		logOutUser: function(){
			fetch('/api/logout', {
                credentials: 'include',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        method: 'POST',
        //body: getBody(this.existingUserInput)
    })
    .then(function (data) {
				if (!data.ok) {
        throw new Error("HTTP error, status = " + data.status);
      	}
        console.log('Fetch succeeded and with server response: ', data);
				console.log("Log Out Status: " + data.status);				
				myVue.auth = false;
				document.location.reload();
    })
    .catch(function (error) {
        console.log(error);
    });
		},		
		joinGame: function(gameId){
			console.log("El game id es: " + gameId);
			fetch('/api/game/' + gameId + '/players', {
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: ""
    		})
				
			.then(function(response){
				console.log("Join Game status: " + response.status);
				//if (!response.ok) {
        //throw new Error("HTTP error, status = " + response.status);
      	//}
				return response.json();				
			})
    	.then(function (data) {
				if(data.error){
					alert(data.error);
				}
				else{
					console.log("New player joined. The new gamePlayer es: " + data.gpid);				
					document.location.href="/web/game.html?gp=" + data.gpid;
				}
				
    	})
    	.catch(function (error) {
        alert('Request failure: ', error);
    	});
		},		
		continueGame: function(game){
			var gpContinueGame = "";
			for(var i = 0; i < game.gameplayers.length; i++){
				if(game.gameplayers[i].player.id == this.gamesJson.logged_player.pid){
					gpContinueGame = game.gameplayers[i].id;
				}
			}
			if(gpContinueGame == ""){
				alert("You are not a player in this game and are not allowed to enter the game");
				document.location.href = "/web/games.html";
			}
			else{
				document.location.href="/web/game.html?gp=" + gpContinueGame;
			}
		},
		/*
		gameOver: function(){			
			document.location.href="/web/games.html";
		},
		*/
		createGame: function(){
			fetch('/api/games', {
        credentials: 'include',       
				headers: {
            'Content-Type': 'application/json'
        },				
        method: 'POST',
        body: ""
    })
    .then(function (response) {
//				if (!response.ok) {
//        throw new Error("HTTP error, status = " + response.status);
//      	}
       return response.json();
    })
			.then(function(data){
				var gpJustCreated = data.gpid;
				alert("El retorno del Post me trae un data.gp = " + gpJustCreated);
				//alert("New Game created with new gamePlayer: " + gpJustCreated);
				//myVue.getGamesInfo();
				document.location.href = "/web/game.html?gp=" + gpJustCreated;
			})
    .catch(function (error) {
        console.log('Request failure: ', error);
    });
		}
	},
	created: function () {
		this.getGamesInfo();
		this.getLeaderBoardInfo();
	},
	computed: {
		successfulLogIn: function(){	
			return this.gamesJson.logged_player == "guest";			
		}
	}
});

//myVue.getGames();
//myVue.logInUser();
//myVue.signUpUser();


// ====================== FUNCTIONS ======================

function getBody(json) {
    var body = [];
    for (var key in json) {
			if(key == 'userName' || key == 'password'){
        var encKey = encodeURIComponent(key);
        var encVal = encodeURIComponent(json[key]);
        body.push(encKey + "=" + encVal);
			}
    }
	console.log(body);
    return body.join("&");
}

//newUserInput: {firstName: "", lastName: "", userName: "", password: ""},
