let svgPlot;
let points = [];
let lastR;

const viewPointsPerUnit = 60;
let svgWidth;
let svgHeight;

document.addEventListener("DOMContentLoaded", () => {
    svgPlot = document.querySelector("svg");
    svgWidth = svgPlot.viewBox.baseVal.width;
    svgHeight = svgPlot.viewBox.baseVal.height;
    svgPlot.addEventListener("click", onSvgClick);
    drawPlot(lastR);
});

function onSvgClick(event) {
    let pos = getMousePosition(this, lastR, event);

    let yButtons = PF("yCoord");
    yButtons.select($(yButtons.buttons[0]));

    PF("xCoord").setValue(pos.x.toFixed(2));
    PF("yCoordHidden").setValue(pos.y.toFixed(2));

    let submitButton = document.getElementById("rChecksForm:submitButton");
    submitButton.click();
}

function getMousePosition(svg, r, event) {
    let rect = svg.getBoundingClientRect();
    let paint_x = (event.clientX - rect.left) / rect.width * svgWidth;
    let paint_y = (event.clientY - rect.top) / rect.height * svgHeight;
    return {
        x: (paint_x - svgWidth / 2) / (viewPointsPerUnit * lastR / r),
        y: (svgHeight / 2 - paint_y) / (viewPointsPerUnit * lastR / r)
    };
}

function createPlotFigure(tagName, attributes) {
    let result = document.createElementNS("http://www.w3.org/2000/svg", tagName);
    for (const key in attributes) {
        result.setAttribute(key, attributes[key]);
    }
    return result;
}

function drawCartesian() {
    let abscissa = createPlotFigure("line", {
        x1: 0,
        y1: svgHeight / 2,
        x2: svgWidth,
        y2: svgHeight / 2,
        stroke: "black"
    });
    svgPlot.insertAdjacentElement("beforeend", abscissa);

    let arrowX = createPlotFigure("polygon", {
        points: `${svgWidth},${svgHeight/2} ${svgWidth - 5},${svgHeight/2 + 5} ${svgWidth - 5},${svgHeight/2 - 5}`,
        fill: "black",
        stroke: "black"
    });
    svgPlot.insertAdjacentElement("beforeend", arrowX);

    let ordinate = createPlotFigure("line", {
        x1: svgWidth / 2,
        y1: 0,
        x2: svgWidth / 2,
        y2: svgHeight,
        stroke: "black"
    });
    svgPlot.insertAdjacentElement("beforeend", ordinate);

    let arrowY = createPlotFigure("polygon", {
        points: `${svgWidth/2},0 ${svgWidth/2 - 5},5 ${svgWidth/2 + 5},5`,
        fill: "black",
        stroke: "black"
    });
    svgPlot.insertAdjacentElement("beforeend", arrowY);
}

function drawPoint(p, r) {
    let scale = r / p.r;
    let point = createPlotFigure("circle", {
        cx: svgWidth / 2 + p.x * scale,
        cy: svgHeight / 2 - p.y * scale,
        r: 5,
        fill: (p.inside ? "green" : "red"),
        stroke: "firebrick"
    });
    point.setAttribute("fill", p.inside ? "green" : "red");
    point.setAttribute("stroke", "firebrick");
    svgPlot.insertAdjacentElement("beforeend", point);
}

function drawSector(r) {
    let sector = createPlotFigure("path", {
        d: [
            "M", (svgWidth / 2 - r / 2), (svgHeight / 2),
            "A", r / 2, r / 2, 0, 0, 0, svgWidth / 2, svgHeight / 2 + r / 2,
            "L", svgWidth / 2, svgHeight / 2,
            "Z"
        ].join(" "),
        fill: "blue",
        "fill-opacity": "0.4",
        stroke: "navy"
    });
    svgPlot.insertAdjacentElement("beforeend", sector);
}

function drawRect(r) {
    let rect = createPlotFigure("rect", {
        x: svgWidth / 2 - r,
        y: svgHeight / 2 - r / 2,
        width: r,
        height: r / 2,
        fill: "blue",
        "fill-opacity": "0.4",
        stroke: "navy"
    });
    svgPlot.insertAdjacentElement("beforeend", rect);
}

function drawTriangle(r) {
    let triangle = createPlotFigure("polygon", {
        points: `${svgWidth/2},${svgHeight/2} ${svgWidth/2 + r},${svgHeight/2} ${svgWidth/2},${svgHeight/2 + r / 2}`,
        fill: "blue",
        "fill-opacity": "0.4",
        stroke: "navy"
    });
    svgPlot.insertAdjacentElement("beforeend", triangle);
}

function drawPlot(r) {
    let drawR = r * viewPointsPerUnit;

    svgPlot.innerHTML = "";
    drawCartesian();
    drawSector(drawR);
    drawRect(drawR);
    drawTriangle(drawR);
    for (const p of points) {
        drawPoint(p, drawR);
    }

    lastR = r;
}
