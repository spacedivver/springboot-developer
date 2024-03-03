// 자바스크립트 사용
// 삭제 기능
// 1. HTML에서id를delete-btn으로 설정한 엘리먼트를 찾아 그 엘리먼트에서 클릭 이벤트가 발생하면
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, { // 2. fetch() 메서드를 통해/api/articles/DELETE 요청을 보냄
            method: 'DELETE'
        })
            .then(() => { // 3. fetch() 실행 후then() 실행
                // 4. alert()는then() 실행 시점에 웹 브라우저 화면으로 삭제 완료 알림 팝업 띄움
                alert('삭제가 완료되었습니다.');
                // 5. location.replace()는 실행 시 사용자 웹 브라우저 화면을 현재 주소를 기반해 옮겨주는 역할
                location.replace('/articles');
            });
    });
}

// API 구현
// 수정 기능
// 1. id가modify-bin인 엘리먼트 조회
const modifyButton = document.getElementById('modify-btn');

if (modifyButton) { // 2. 클릭 이벤트가 감지되면 수정API 요청
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/articles/${id}`, { // 3. 수정API로/api/articles/ PUT 요청
            method: 'PUT',
            headers: {
                "Content-Type": "application/json", // 4. headers에 요청 형식 지정
            },
            body: JSON.stringify({ // 5. body에HTML에 입력한 데이터를JSON 형식으로 바꿔 보냄
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
            .then(() => { // 6. 요청이 완료되면then()으로 마무리 작업
                alert('수정이 완료되었습니다.');
                location.replace(`/articles/${id}`);
            });
    });
}

// 생성 기능
// 1. id가 create-bin인 엘리먼트 찾기
const createButton = document.getElementById('create-btn');

if (createButton) { // 2. 찾은 엘리먼트에서 클릭 이벤트가 감지되면 엘리먼트 값을 가져와
    createButton.addEventListener('click', event => {
        fetch('/api/articles', { // 3. 생성 API로 /api/articles/ POST 요청 보냄
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
            .then(() => {
                alert('등록 완료되었습니다.');
                location.replace('/articles');
            });
    });
}