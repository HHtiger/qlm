#!/bin/bash
# -*- coding: utf-8 -*-  
from flask import Flask
from flask import request

app = Flask(__name__)

registerusername = {}

@app.route('/train/register')
def index():
    name = request.values.get("registerusername")
    if registerusername.get(name) == None:
        registerusername[name] = 1
        return "register success"
    else:
        return "already used"

if __name__ == '__main__':
    app.run(host='23.105.193.13', port=8080)
