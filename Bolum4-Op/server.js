const { spawn } = require('child_process');
const WebSocket = require('ws');

const wss = new WebSocket.Server({ port: 8080 });

const mcServer = spawn('java', ['-Xmx2G', '-Xms2G', '-jar', 'paper.jar', 'nogui']);

wss.on('connection', (ws) => {
    const sendLog = (data) => {
        ws.send(data.toString());
    };

    mcServer.stdout.on('data', sendLog);
    mcServer.stderr.on('data', sendLog);

    ws.on('message', (msg) => {
        mcServer.stdin.write(msg + '\n');
    });

    ws.on('close', () => {
        mcServer.stdout.off('data', sendLog);
        mcServer.stderr.off('data', sendLog);
    });
});