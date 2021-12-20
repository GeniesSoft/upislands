import styled from 'styled-components';

const GuidesWrapper = styled.div`
  padding: 29px 0;

  .guides_title {
    margin-bottom: 30px;
  }

  a {
    &:hover {
      color: #31b8bd;
    }
  }
  
  .card-image {
    width: 240px;
    height: 240px;
  }
  
  img {
    width: 200px;
    height: 200px;
    object-fit: cover;
    border-radius: 50%;
  }
  
  .view-details {
    padding: 10px 0 0 0;
  }
  
  .view-details:hover {
    display: block;
  }
  
`;

export const GuidesArea = styled.div`
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  justify-content: space-between;
  margin-bottom: -15px;

  > div {
    width: calc(100% / 4 - 10px);

    @media (max-width: 767px) {
      width: calc(100% / 3 - 10px);
      margin-bottom: 20px;
    }

    @media (max-width: 580px) {
      width: calc(100% / 2 - 10px);
      margin-bottom: 20px;
    }
  }
`;

export default GuidesWrapper;
