#!/bin/bash
# -*- coding: utf-8 -*-  

from flask import Flask
from flask import request

app = Flask(__name__)

@app.route('/train/plus')
def index():
    a = int(request.values.get("a"))
    b = int(request.values.get("b"))
    return "%d" % (a*b)

if __name__ == '__main__':
    app.run(host='ip', port=8080)
