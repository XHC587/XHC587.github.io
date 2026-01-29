// 全局变量
let currentScreen = 'login';
let currentImagePath = 'images/tupian/';
let gameData = [];
let emptyX = 0;
let emptyY = 0;
let stepCount = 0;
let victory = false;

// 用户数据（模拟数据库）
let users = [
    { username: 'zhangsan', password: '123' },
    { username: 'lisi', password: '1234' }
];

// 验证码相关
let currentCaptcha = '';

// 页面加载完成后执行
document.addEventListener('DOMContentLoaded', function() {
    generateCaptcha();
    initializeEventListeners();
});

// 生成验证码
function generateCaptcha() {
    const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let captcha = '';
    for (let i = 0; i < 5; i++) {
        captcha += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    currentCaptcha = captcha;
    document.getElementById('captcha-display').textContent = captcha;
}

// 初始化事件监听器
function initializeEventListeners() {
    // 登录界面事件
    document.getElementById('login-btn').addEventListener('click', handleLogin);
    document.getElementById('register-btn').addEventListener('click', function() {
        showScreen('register');
    });
    document.getElementById('refresh-captcha').addEventListener('click', generateCaptcha);

    // 注册界面事件
    document.getElementById('reg-submit').addEventListener('click', handleRegister);
    document.getElementById('back-to-login').addEventListener('click', function() {
        showScreen('login');
    });

    // 游戏界面事件
    document.getElementById('replay-btn').addEventListener('click', resetGame);
    document.getElementById('re-login-btn').addEventListener('click', function() {
        showScreen('login');
    });
    document.getElementById('close-btn').addEventListener('click', function() {
        if (confirm('确定要关闭游戏吗？')) {
            window.close();
        }
    });
    document.getElementById('about-btn').addEventListener('click', function() {
        document.getElementById('about-modal').classList.remove('hidden');
    });

    // 图片选择事件
    document.querySelectorAll('.img-option').forEach(option => {
        option.addEventListener('click', function() {
            const imgNum = this.dataset.img;
            currentImagePath = `images/tupian-${imgNum === '1' ? '' : '-' + imgNum}/`;
            resetGame();
        });
    });

    // 游戏完成事件
    document.getElementById('caidan-btn').addEventListener('click', showEasterEgg);

    // 关闭弹窗事件
    document.querySelectorAll('.modal').forEach(modal => {
        modal.addEventListener('click', function(e) {
            if (e.target === this) {
                this.classList.add('hidden');
            }
        });
    });

    // 键盘事件
    document.addEventListener('keydown', handleKeyDown);
    document.addEventListener('keyup', handleKeyUp);
}

// 显示指定屏幕
function showScreen(screenName) {
    // 隐藏所有屏幕
    document.querySelectorAll('.screen').forEach(screen => {
        screen.classList.add('hidden');
    });

    // 显示指定屏幕
    document.getElementById(`${screenName}-screen`).classList.remove('hidden');
    currentScreen = screenName;

    // 如果是游戏屏幕，初始化游戏
    if (screenName === 'game') {
        initializeGame();
    }
}

// 处理登录
function handleLogin() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const captcha = document.getElementById('captcha').value;

    // 验证验证码
    if (captcha.toLowerCase() !== currentCaptcha.toLowerCase()) {
        alert('验证码错误');
        generateCaptcha();
        return;
    }

    // 验证用户名和密码
    const user = users.find(u => u.username === username && u.password === password);
    if (user) {
        showScreen('game');
    } else {
        alert('用户名或密码错误');
    }
}

// 处理注册
function handleRegister() {
    const username = document.getElementById('reg-username').value;
    const password = document.getElementById('reg-password').value;
    const confirm = document.getElementById('reg-confirm').value;

    if (!username || !password) {
        alert('用户名和密码不能为空');
        return;
    }

    if (password !== confirm) {
        alert('两次输入的密码不一致');
        return;
    }

    // 检查用户名是否已存在
    if (users.find(u => u.username === username)) {
        alert('用户名已存在');
        return;
    }

    // 添加新用户
    users.push({ username, password });
    alert('注册成功');
    showScreen('login');
}

// 初始化游戏
function initializeGame() {
    resetGame();
}

// 重置游戏
function resetGame() {
    // 初始化游戏数据
    gameData = [];
    const tempArr = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15];
    
    // 打乱数组
    for (let i = tempArr.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [tempArr[i], tempArr[j]] = [tempArr[j], tempArr[i]];
    }

    // 填充二维数组
    for (let i = 0; i < 4; i++) {
        gameData[i] = [];
        for (let j = 0; j < 4; j++) {
            gameData[i][j] = tempArr[i * 4 + j];
            if (tempArr[i * 4 + j] === 0) {
                emptyX = i;
                emptyY = j;
            }
        }
    }

    // 重置步数和胜利状态
    stepCount = 0;
    victory = false;
    updateStepCount();

    // 渲染游戏板
    renderGameBoard();
}

// 渲染游戏板
function renderGameBoard() {
    const gameBoard = document.getElementById('game-board');
    gameBoard.innerHTML = '';

    for (let i = 0; i < 4; i++) {
        for (let j = 0; j < 4; j++) {
            const piece = document.createElement('div');
            piece.className = 'puzzle-piece';
            
            if (gameData[i][j] !== 0) {
                piece.style.backgroundImage = `url(${currentImagePath}chunk_${gameData[i][j]}.png)`;
                piece.style.backgroundPosition = `${-j * 125}px ${-i * 166}px`;
                piece.addEventListener('click', () => handlePieceClick(i, j));
            } else {
                piece.style.backgroundColor = 'transparent';
            }

            gameBoard.appendChild(piece);
        }
    }
}

// 处理拼图块点击
function handlePieceClick(x, y) {
    if (victory) return;

    // 检查是否可以移动到空白位置
    if ((Math.abs(x - emptyX) === 1 && y === emptyY) || (Math.abs(y - emptyY) === 1 && x === emptyX)) {
        // 交换位置
        [gameData[emptyX][emptyY], gameData[x][y]] = [gameData[x][y], gameData[emptyX][emptyY]];
        
        // 更新空白位置
        emptyX = x;
        emptyY = y;
        
        // 更新步数
        stepCount++;
        updateStepCount();
        
        // 重新渲染游戏板
        renderGameBoard();
        
        // 检查是否胜利
        checkVictory();
    }
}

// 处理键盘按下事件
function handleKeyDown(e) {
    if (currentScreen !== 'game') return;

    // 空格键显示完整图片
    if (e.code === 'Space') {
        e.preventDefault();
        showFullImage();
    }
}

// 处理键盘松开事件
function handleKeyUp(e) {
    if (currentScreen !== 'game' || victory) return;

    // 空格键隐藏完整图片
    if (e.code === 'Space') {
        e.preventDefault();
        hideFullImage();
        return;
    }

    // Alt键一键完成
    if (e.altKey) {
        completeGame();
        return;
    }

    // 方向键移动
    let newX = emptyX;
    let newY = emptyY;

    switch (e.code) {
        case 'ArrowUp':
            newX++;
            break;
        case 'ArrowDown':
            newX--;
            break;
        case 'ArrowLeft':
            newY++;
            break;
        case 'ArrowRight':
            newY--;
            break;
        default:
            return;
    }

    // 检查移动是否有效
    if (newX >= 0 && newX < 4 && newY >= 0 && newY < 4) {
        // 交换位置
        [gameData[emptyX][emptyY], gameData[newX][newY]] = [gameData[newX][newY], gameData[emptyX][emptyY]];
        
        // 更新空白位置
        emptyX = newX;
        emptyY = newY;
        
        // 更新步数
        stepCount++;
        updateStepCount();
        
        // 重新渲染游戏板
        renderGameBoard();
        
        // 检查是否胜利
        checkVictory();
    }
}

// 显示完整图片
function showFullImage() {
    let fullImage = document.getElementById('full-image');
    if (!fullImage) {
        fullImage = document.createElement('img');
        fullImage.id = 'full-image';
        fullImage.className = 'full-image';
        fullImage.src = currentImagePath + '1-1.png';
        document.body.appendChild(fullImage);
    } else {
        fullImage.classList.remove('hidden');
    }
}

// 隐藏完整图片
function hideFullImage() {
    const fullImage = document.getElementById('full-image');
    if (fullImage) {
        fullImage.classList.add('hidden');
    }
}

// 一键完成游戏
function completeGame() {
    // 重置游戏数据为正确顺序
    for (let i = 0; i < 4; i++) {
        for (let j = 0; j < 4; j++) {
            gameData[i][j] = i * 4 + j;
        }
    }
    
    // 更新空白位置
    emptyX = 0;
    emptyY = 0;
    
    // 重新渲染游戏板
    renderGameBoard();
    
    // 检查是否胜利
    checkVictory();
}

// 检查是否胜利
function checkVictory() {
    for (let i = 0; i < 4; i++) {
        for (let j = 0; j < 4; j++) {
            if (gameData[i][j] !== i * 4 + j) {
                return;
            }
        }
    }
    
    victory = true;
    showVictoryModal();
}

// 显示胜利弹窗
function showVictoryModal() {
    const modal = document.getElementById('victory-modal');
    const victoryImage = document.getElementById('victory-image');
    
    // 清空内容
    victoryImage.innerHTML = '';
    
    // 创建完整图片
    const img = document.createElement('img');
    img.src = currentImagePath + '1-1.png';
    img.style.maxWidth = '500px';
    img.style.maxHeight = '664px';
    
    victoryImage.appendChild(img);
    modal.classList.remove('hidden');
}

// 显示彩蛋
function showEasterEgg() {
    const modal = document.getElementById('easter-egg-modal');
    const eggImage = document.getElementById('easter-egg-image');
    
    // 确定彩蛋图片路径
    const pathParts = currentImagePath.split('-');
    const imgNum = pathParts.length > 1 ? pathParts[1].split('/')[0] : '1';
    
    eggImage.src = `images/TTPZ/${imgNum}.png`;
    modal.classList.remove('hidden');
    
    // 隐藏胜利弹窗
    document.getElementById('victory-modal').classList.add('hidden');
}

// 更新步数显示
function updateStepCount() {
    document.getElementById('step-count').textContent = `步数: ${stepCount}`;
}